package cpta;

import cpta.environment.Compiler;
import cpta.environment.Executer;
import cpta.exam.ExamSpec;
import cpta.exam.Problem;
import cpta.exam.Student;
import cpta.exam.TestCase;
import cpta.exceptions.CompileErrorException;
import cpta.exceptions.InvalidFileTypeException;
import cpta.exceptions.RunTimeErrorException;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Grader {
    Compiler compiler;
    Executer executer;

    public Grader(Compiler compiler, Executer executer) {
        this.compiler = compiler;
        this.executer = executer;
    }

    public Map<String,Map<String, List<Double>>> gradeSimple(ExamSpec examSpec, String submissionDirPath) {
        List<Problem> problems = examSpec.problems;
        List<Student> students = examSpec.students;
        Map<String, Map<String, List<Double>>> totalGrade = new HashMap<>();

        // Compilation
        for(Student student : students) {
            Map<String, List<Double>> studentGrade = new HashMap<>();
            String stuDir = submissionDirPath + student.id;
            for (Problem problem : problems) {
                String probDirPath = Paths.get(stuDir, problem.id).toString();
                // Compilation
                String sugoDir = Paths.get(probDirPath, problem.targetFileName).toString();
                try {
                    compiler.compile(sugoDir);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Execution & Scoring
                List<Double> scoreList = new ArrayList<>();
                try {
                    String testDir = problem.testCasesDirPath;
                    List<TestCase> testCases = problem.testCases;
                    for (TestCase tc : testCases) {
                        String inDir = Paths.get(testDir, tc.inputFileName).toString();
                        String tcOutDir = Paths.get(testDir, tc.outputFileName).toString();
                        String yoDir = Paths.get(probDirPath, problem.targetFileName).toString().replaceFirst("[.][^.]+$", ".yo");
                        String scOutDir = Paths.get(probDirPath, tc.outputFileName).toString();

                        executer.execute(yoDir, inDir, scOutDir);

                        String expected = new Scanner(new File(tcOutDir)).useDelimiter("\\Z").next();
                        String executed = new Scanner(new File(scOutDir)).useDelimiter("\\Z").next();

                        if (expected.equals(executed)) {
                            scoreList.add(tc.score);
                        } else {
                            scoreList.add(0.0);
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                studentGrade.put(problem.id, scoreList);
            }
            totalGrade.put(student.id, studentGrade);
        }
        return totalGrade;
    }

    public Map<String,Map<String, List<Double>>> gradeRobust(ExamSpec examSpec, String submissionDirPath) {
        List<Problem> problems = examSpec.problems;
        List<Student> students = examSpec.students;
        Map<String, Map<String, List<Double>>> totalGrade = new HashMap<>();

        File[] studentsSubDir = new File(submissionDirPath).listFiles();

        for(Student student : students) {
            Map<String, List<Double>> studentGrade = new HashMap<>();
            String stuDir = submissionDirPath + student.id;
            boolean examSubmission = true;

            // Group 4 - No Submission of Exam
            examSubmission = new File(stuDir).exists();

            // Group 4 - Wrong Directory Name
            if(!examSubmission){
                for(File file : studentsSubDir){
                    if(file.getName().startsWith(student.id)){
                        stuDir = Paths.get(submissionDirPath, file.getName()).toString();

                        // Group 4 - No Submission of Exam
                        examSubmission = true;
                        break;
                    }
                }
            }

            for (Problem problem : problems) {
                boolean CompileError = false;
                boolean RuntimeError = false;
                boolean onlyYoPenalty = false;
                boolean probSubmission = true;
                String probDirPath = Paths.get(stuDir, problem.id).toString();
                File probDir = new File(probDirPath);


                // Group 4 - No Submission of Problem
                probSubmission = examSubmission && probDir.exists();

                // Group 4 - Wrong directory structure : Move all the files out and delete the directory
                if(probSubmission) {
                    File[] addDir = probDir.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                            return file.isDirectory();
                        }
                    });
                    if(addDir.length > 0) {
                        for (File file : addDir[0].listFiles()) {
                            file.renameTo(new File(probDir, file.getName()));
                        }
                        addDir[0].delete();
                    }
                }

                // Group 3 - Wrapper codes
                if(probSubmission){

                    if(problem.wrappersDirPath != null){
                        File[] sugoFiles = new File(problem.wrappersDirPath).listFiles(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith(".sugo");
                            }
                        });

                        for(File sugoFile : sugoFiles){
                            Path sourceFilePath = Paths.get(sugoFile.getPath());
                            Path destFilePath = Paths.get(probDirPath, sugoFile.getName());
                            try{
                                Files.copy(sourceFilePath, destFilePath);
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // Group 4 - Submitted only .yo files & unnecessary .yo files
                // Group 4 - No Submission
                if(probSubmission){
                    File[] sugoFiles = probDir.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.endsWith(".sugo");
                        }
                    });
                    File[] yoFiles = probDir.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.endsWith(".yo");
                        }
                    });

                    for(File yoFile : yoFiles){
                        onlyYoPenalty = true;
                        String fileName = yoFile.getName().replaceFirst("[.][^.]+$", "");
                        if(sugoFiles.length == 0){
                            break;
                        } else {
                            for (File sugoFile : sugoFiles) {
                                if (sugoFile.getName().replaceFirst("[.][^.]+$", "").equals(fileName)) {
                                    onlyYoPenalty = false;
                                    break;
                                }
                            }
                            if(onlyYoPenalty){
                                break;
                            }
                        }
                    }
                }

                // Compilation & Group 1 - Compile Error
                if(probSubmission){
                    File[] sugoFiles = probDir.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().endsWith(".sugo");
                        }
                    });

                    for(File sugoFile : sugoFiles){

                        try{
                            compiler.compile(sugoFile.getPath());
                        } catch(CompileErrorException e){
                            CompileError = true;
                            e.printStackTrace();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                // Execution & Scoring
                List<Double> scoreList = new ArrayList<>();
                String testDir = problem.testCasesDirPath;
                List<TestCase> testCases = problem.testCases;

                for (TestCase tc : testCases) {
                    String expected = null;
                    String executed = null;

                    String inDir = Paths.get(testDir, tc.inputFileName).toString();
                    String tcOutDir = Paths.get(testDir, tc.outputFileName).toString();
                    String yoDir = Paths.get(probDirPath, problem.targetFileName).toString().replaceFirst("[.][^.]+$", ".yo");
                    String scOutDir = Paths.get(probDirPath, tc.outputFileName).toString();

                    try {
                        executer.execute(yoDir, inDir, scOutDir);
                    } catch(RunTimeErrorException e){
                        RuntimeError = true;
                        e.printStackTrace();
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                    try {
                        if(new File(tcOutDir).exists()) {
                            expected = new Scanner(new File(tcOutDir)).useDelimiter("\\Z").next();
                        } else{
                            expected = null;
                        }
                        if(new File(scOutDir).exists()) {
                            executed = new Scanner(new File(scOutDir)).useDelimiter("\\Z").next();
                        } else{
                            executed = null;
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                    if(expected != null && executed != null && problem.judgingTypes != null) {
                        // Group 2 - Trailing whitespaces
                        if (problem.judgingTypes.contains(Problem.TRAILING_WHITESPACES)) {
                            expected = expected.replaceFirst("\\s++$", "");
                            executed = executed.replaceFirst("\\s++$", "");
                        }

                        // Group 2 - Ignore whitespaces
                        if (problem.judgingTypes.contains(Problem.IGNORE_WHITESPACES)) {
                            expected = expected.replaceAll("\\s", "");
                            executed = executed.replaceAll("\\s", "");
                        }

                        // Group 2 - Case-insensitive
                        if (problem.judgingTypes.contains(Problem.CASE_INSENSITIVE)) {
                            expected = expected.toLowerCase();
                            executed = executed.toLowerCase();
                        }
                    }

                    if (!examSubmission || !probSubmission || RuntimeError || CompileError) {
                        scoreList.add(0.0);
                    } else if (expected.equals(executed)) {
                        if(onlyYoPenalty) scoreList.add(tc.score / 2);
                        else scoreList.add(tc.score);
                    } else{
                        scoreList.add(0.0);
                    }
                }
                studentGrade.put(problem.id, scoreList);
            }
            totalGrade.put(student.id, studentGrade);
        }
        return totalGrade;
    }

}

