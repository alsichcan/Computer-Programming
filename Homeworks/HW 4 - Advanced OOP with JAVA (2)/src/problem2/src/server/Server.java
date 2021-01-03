package server;

import course.*;
import utils.Config;
import utils.ErrorCode;
import utils.Pair;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Server {
    String courseDataPath = "data/Courses/2020_Spring/";
    String userDataPath = "data/Users/";
    Set<Course> courseDB;
    Map<Integer, Course> idDB;
    Map<String, List<Course>> deptDB;
    Map<Integer, List<Course>> ayDB;

    public Server(){
        courseDB = new HashSet<>();
        idDB = new HashMap<>();
        deptDB = new HashMap<>();
        ayDB = new HashMap<>();
        loadCourses();
    }

    private void loadCourses(){
        File dir = new File(courseDataPath);
        File[] collegeList = dir.listFiles();

        for(File college : collegeList) {
            File[] courseList = college.listFiles();
            String collegeName = college.getName().replaceFirst("[.][^.]+$", "");

            for (File course : courseList) {
                try{
                    FileReader fr = new FileReader(course);
                    BufferedReader br = new BufferedReader(fr);

                    int courseId = Integer.parseInt(course.getName().replaceFirst("[.][^.]+$", ""));

                    String courseInfo = br.readLine();
                    String[] cis = courseInfo.split("\\|");

                    Course c = new Course(courseId, collegeName, cis[0], cis[1], Integer.parseInt(cis[2]), cis[3], Integer.parseInt(cis[4]), cis[5], cis[6], Integer.parseInt(cis[7]));

                    // Initialize courseDB
                    courseDB.add(c);

                    // Initialize idDB
                    idDB.put(c.courseId, c);

                    // Initialize deptDB
                    if(deptDB.containsKey(c.department)){
                        deptDB.get(c.department).add(c);
                    } else{
                        List<Course> cList = new LinkedList<>();
                        cList.add(c);
                        deptDB.put(c.department, cList);
                    }

                    // Initialize ayDB
                    if(ayDB.containsKey(c.academicYear)){
                        ayDB.get(c.academicYear).add(c);
                    } else{
                        List<Course> cList = new LinkedList<>();
                        cList.add(c);
                        ayDB.put(c.academicYear, cList);
                    }

                    br.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Course> search(Map<String,Object> searchConditions, String sortCriteria){
        Set<Course> totalCourse = new HashSet<>(courseDB);

        if(!(searchConditions == null && searchConditions.isEmpty())){
            String dept = (String) searchConditions.get("dept");
            Integer ay = (Integer) searchConditions.get("ay");
            String name = (String) searchConditions.get("name");

            Set<Course> courseList_dept = totalCourse;
            Set<Course> courseList_ay = totalCourse;
            Set<Course> courseList_name = totalCourse;

            if(dept != null) {
                List<Course> deptCourse = deptDB.get(dept);
                if(deptCourse != null) courseList_dept = new HashSet<>(deptCourse);
                else courseList_dept = new HashSet<>();
            }
            if(ay != null){
                List<Course> ayCourse = ayDB.get(ay);
                if(ayCourse != null) courseList_ay = new HashSet<>(ayCourse);
                else courseList_ay = new HashSet<>();
            }
            if(name != null){ // TODO : Debug?
                String[] kwords = name.split("\\s");
                Set<String> kwordSet = new HashSet<>(Arrays.asList(kwords));

                Set<Course> searched = new HashSet<>();
                for(Course course : totalCourse){
                    String[] cwords = course.courseName.split("\\s");
                    Set<String> cwordSet = new HashSet<>(Arrays.asList(cwords));
                    if(cwordSet.containsAll(kwordSet)){
                        searched.add(course);
                    }
                }
                courseList_name = searched;
            }

            totalCourse.retainAll(courseList_dept);
            totalCourse.retainAll(courseList_ay);
            totalCourse.retainAll(courseList_name);
        }

        List<Course> searchedList = new LinkedList<>(totalCourse);

        if(sortCriteria == null || sortCriteria.isEmpty() || sortCriteria.equals("id")){
            Collections.sort(searchedList, new Comparator<Course>(){
               @Override
               public int compare(Course c1, Course c2){
                   int id1 = c1.courseId;
                   int id2 = c2.courseId;
                   return id1 < id2 ? -1 : (id1 > id2 ? 1 : 0) ;
               }
            });
        } else if(sortCriteria.equals("name")){
            Collections.sort(searchedList, new Comparator<Course>(){
               @Override
               public int compare(Course c1, Course c2){
                   String name1 = c1.courseName;
                   String name2 = c2.courseName;
                   if(name1.compareTo(name2) < 0) return -1;
                   else if(name1.compareTo(name2) > 0) return 1;
                   else return c1.courseId < c2.courseId ? -1 : (c1.courseId > c2.courseId ? 1 : 0);
               }
            });
        } else if(sortCriteria.equals("dept")){
            Collections.sort(searchedList, new Comparator<Course>(){
                @Override
                public int compare(Course c1, Course c2){
                    String dept1 = c1.department;
                    String dept2 = c2.department;
                    if(dept1.compareTo(dept2) < 0) return -1;
                    else if(dept1.compareTo(dept2) > 0) return 1;
                    else return c1.courseId < c2.courseId ? -1 : (c1.courseId > c2.courseId ? 1 : 0);
                }
            });
        } else if(sortCriteria.equals("ay")){
            Collections.sort(searchedList, new Comparator<Course>(){
                @Override
                public int compare(Course c1, Course c2){
                    int ay1 = c1.academicYear;
                    int ay2 = c2.academicYear;
                    if(ay1 < ay2) return -1;
                    else if(ay1 > ay2) return 1;
                    else return c1.courseId < c2.courseId ? -1 : (c1.courseId > c2.courseId ? 1 : 0);
                }
            });
        }

        return searchedList;
    }

    public int bid(int courseId, int mileage, String userId){
        Pair<Integer, List<Bidding>> rBids = retrieveBids(userId);
        User user = new User(userId, rBids.value);

        if(rBids.key == ErrorCode.USERID_NOT_FOUND) return ErrorCode.USERID_NOT_FOUND;
        if(rBids.key == ErrorCode.IO_ERROR) return ErrorCode.IO_ERROR;
        if(!idDB.containsKey(courseId)) return ErrorCode.NO_COURSE_ID;

        return user.bid(courseId, mileage);
    }

    public Pair<Integer,List<Bidding>> retrieveBids(String userId) {
        String userDir = Paths.get(userDataPath, userId).toString();

        File f = new File(userDir);
        if(!f.exists() || !f.getName().equals(userId)){
            return new Pair<>(ErrorCode.USERID_NOT_FOUND, new ArrayList<>());
        }

        String userBidDir = userDir + "/bid.txt";
        if(!new File(userBidDir).exists()) return new Pair<>(ErrorCode.IO_ERROR, new ArrayList<>());

        List<Bidding> biddingList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(userBidDir);
            BufferedReader br = new BufferedReader(fr);

            String bidInfo = null;

            while ((bidInfo = br.readLine()) != null) {
                String[] info = bidInfo.split("\\|");
                biddingList.add(new Bidding(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
            }
            br.close();
        } catch (Exception e) {
            return new Pair<>(ErrorCode.IO_ERROR, new ArrayList<>());
        }

        return new Pair<>(ErrorCode.SUCCESS, biddingList);
    }

    public boolean confirmBids(){
        File dir = new File(userDataPath);
        File[] userList = dir.listFiles();
        HashMap<String, User> userDB = new HashMap<>();
        HashMap<Integer, TreeMap<Integer, LinkedList<User>>> bidDB = new HashMap<>();

        // bidDB initialization
        for(File user : userList){
            String userID = user.getName().replaceFirst("[.][^.]+$", "");
            List<Bidding> bidList = retrieveBids(userID).value;
            User usr = new User(userID, bidList);
            userDB.put(userID, usr);

            for(Bidding bid : bidList){
                if(bidDB.containsKey(bid.courseId)){
                    TreeMap<Integer, LinkedList<User>> courseBid = bidDB.get(bid.courseId);
                    if(courseBid.containsKey(bid.mileage)){
                        courseBid.get(bid.mileage).add(usr);
                    } else{
                        LinkedList<User> bidUserList = new LinkedList<>();
                        bidUserList.add(usr);
                        courseBid.put(bid.mileage, bidUserList);
                    }
                    bidDB.put(bid.courseId, courseBid);
                } else{
                    TreeMap<Integer, LinkedList<User>> courseBid = new TreeMap<>();
                    LinkedList<User> bidUserList = new LinkedList<>();
                    bidUserList.add(usr);
                    courseBid.put(bid.mileage, bidUserList);
                    bidDB.put(bid.courseId, courseBid);
                }
            }
        }

        try{
            for(int i : bidDB.keySet()) {
                TreeMap<Integer, LinkedList<User>> bidUserList = bidDB.get(i);
                int mileage = bidUserList.lastKey();
                int leftQuota = idDB.get(i).quota;

                while (leftQuota > 0) {
                    if (mileage == 0) break;
                    if (!bidUserList.containsKey(mileage)) {
                        mileage--;
                        continue;
                    }

                    List<User> lst = bidUserList.get(mileage);
                    Collections.sort(lst, new Comparator<User>() {
                        @Override
                        public int compare(User u1, User u2) {
                            int m1 = u1.getTotalMileage();
                            int m2 = u2.getTotalMileage();
                            String id1 = u1.userID;
                            String id2 = u2.userID;
                            if (m1 < m2) return -1;
                            else if (m1 > m2) return 1;
                            else return id1.compareTo(id2) < 0 ? -1 : 1;
                        }
                    });

                    int count = lst.size();
                    if (count <= leftQuota) {
                        for (User confirmedUser : lst) {
                            confirmedUser.confirm(idDB.get(i));
                        }
                        leftQuota -= count;
                    } else {
                        for (User confirmedUser : lst.subList(0, leftQuota)) {
                            confirmedUser.confirm(idDB.get(i));
                        }
                        leftQuota = 0;
                    }
                    mileage--;
                }
            }
        }catch(IOException e){
            return false;
        }

        // Remove all bid information
        try {
            for (User usr : userDB.values()) {
                usr.emptyBid();
            }
        } catch(Exception e){
            e.printStackTrace();;
        }



        return true;

    }

    public Pair<Integer,List<Course>> retrieveRegisteredCourse(String userId){
        String userDir = userDataPath + userId;

        File f = new File(userDir);
        if(!f.exists()  || !f.getName().equals(userId)) return new Pair<>(ErrorCode.USERID_NOT_FOUND, new ArrayList<>());

        String userRegDir = userDir + "/reg.txt";
        if(!new File(userRegDir).exists()) return new Pair<>(ErrorCode.IO_ERROR, new ArrayList<>());

        List<Course> registeredList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(userRegDir);
            BufferedReader br = new BufferedReader(fr);

            String regInfo = null;

            while ((regInfo = br.readLine()) != null) { ;
                registeredList.add(idDB.get(Integer.parseInt(regInfo)));
            }
            br.close();
        } catch (Exception e) {
            return new Pair<>(ErrorCode.IO_ERROR, new ArrayList<>());
        }

        return new Pair<>(ErrorCode.SUCCESS, registeredList);
    }
}