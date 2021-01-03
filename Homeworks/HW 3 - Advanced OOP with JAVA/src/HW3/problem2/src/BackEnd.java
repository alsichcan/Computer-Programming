import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BackEnd extends ServerResourceAccessible {
    // Use getServerStorageDir() as a default directory
    // TODO sub-program 1 ~ 4 :
    // Create helper funtions to support FrontEnd class

    static int numOfPosts = 0;

    public BackEnd(){
        initNumOfPosts();
    }

    public boolean authCheck(String id, String passwd){
        String path = getServerStorageDir() + id + "/password.txt";
        String userPasswd = null;
        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            userPasswd = br.readLine();
        } catch(Exception e){
            e.printStackTrace();
        }

        if(userPasswd.equals(passwd)) return true;
        else return false;
    }

    public void savePost(User user, Post post){
        post.setId(++numOfPosts);
        FileWriter fileWriter;
        String path = getServerStorageDir() + user.id + "/post" + "/" + String.valueOf(post.getId()) + ".txt";
        try{
           fileWriter = new FileWriter(path);
           fileWriter.write(post.getDate() + "\n");
           fileWriter.write(post.getTitle() + "\n");
           fileWriter.write("\n");
           fileWriter.write(post.getContent());
           fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void initNumOfPosts(){
        int maxNum = -1;
        File dir = new File(getServerStorageDir());
        File[] userList = dir.listFiles();

        for(File user : userList){
            File[] postDir = user.listFiles(new FileFilter(){
                @Override
                public boolean accept(File file){
                    return file.isDirectory() && file.getName().equals("post");
                }
            });

            File[] postList = postDir[0].listFiles();
            for(File post : postList){
                String postname = post.getName().replaceFirst("[.][^.]+$", "");
                if(Integer.parseInt(postname) > maxNum){
                    maxNum = Integer.parseInt(postname);
                }
            }
        }
        numOfPosts = maxNum;
    }

    public List<Post> recommendPost(User user){
        List<String> friendList = new ArrayList<>();

        String path = getServerStorageDir() + "/" + user.id + "/" + "friend.txt";
        File file = new File(path);

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            while((line = br.readLine()) != null){
                friendList.add(line);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        List<Post> postList = new LinkedList<>();

        for(String friend : friendList){
            String dir = getServerStorageDir() + "/" + friend + "/post";
            File postDir = new File(dir);
            for(File pst : postDir.listFiles()){
                try {
                    FileReader fr = new FileReader(pst);
                    BufferedReader br = new BufferedReader(fr);

                    int id = Integer.parseInt(pst.getName().replaceFirst("[.][^.]+$", ""));
                    LocalDateTime dateTime = LocalDateTime.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    String title = br.readLine();
                    br.readLine();

                    String content = "";
                    String contentLine = null;
                    while((contentLine = br.readLine()) != null){
                        content += contentLine + "\n";
                    }
                    content = content.trim();

                    postList.add(new Post(id, dateTime, title, content));
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        Collections.sort(postList, new PostComp());
        List<Post> returnList = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            returnList.add(postList.get(i));
        }
        return returnList;
    }

    public List<Post> searchPost(Set<String> keywords){
        File dir = new File(getServerStorageDir());
        File[] userList = dir.listFiles();
        TreeMap<Integer, LinkedList<Post>> hasKeyMap = new TreeMap<>();

        for(File user : userList){
            File[] postDir = user.listFiles(new FileFilter(){
                @Override
                public boolean accept(File file){
                    return file.isDirectory() && file.getName().equals("post");
                }
            });

            File[] postList = postDir[0].listFiles();
            List<String> wordList;
            String line = null;
            int count; // count of keywords
            boolean hasKeywords;

            for(File post : postList){
                try {
                    wordList = new LinkedList<>();
                    count = 0;
                    hasKeywords = false;

                    FileReader fr = new FileReader(post);
                    BufferedReader br = new BufferedReader(fr);
                    List<String> stringList = new LinkedList<>();
                    while((line = br.readLine()) != null){
                        stringList.add(line);
                        wordList.addAll(Arrays.asList(line.split("\\s+")));
                    }

                    for(String word : wordList){
                        if(keywords.contains(word)){
                            count++;
                            hasKeywords = true;
                        }
                    }

                    if(hasKeywords){
                        int id = Integer.parseInt(post.getName().replaceFirst("[.][^.]+$", ""));
                        LocalDateTime dateTime = LocalDateTime.parse(stringList.get(0), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                        String title = stringList.get(1);

                        String content = "";
                        ListIterator<String> itr = stringList.listIterator(3);
                        while(itr.hasNext()){
                            content += itr.next() + "\n";
                        }
                        content = content.trim();

                        Post pst = new Post(id, dateTime, title, content);

                        if(!hasKeyMap.containsKey(count)){
                            hasKeyMap.put(count, new LinkedList<>());
                        }
                        hasKeyMap.get(count).add(pst);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        List<Post> searchedList = new LinkedList<>();

        if(!hasKeyMap.isEmpty()) {
            int keyCount = hasKeyMap.lastKey();

            int leftPost = 10;
            while (leftPost > 0) {
                if(keyCount == 0) break;
                if(!hasKeyMap.containsKey(keyCount)){
                    keyCount--;
                    continue;
                }

                List<Post> lst = hasKeyMap.get(keyCount);
                Collections.sort(lst, new PostComp());
                int count = lst.size();

                if (count <= leftPost) {
                    searchedList.addAll(lst);
                    leftPost -= count;
                } else {
                    searchedList.addAll(lst.subList(0, leftPost));
                    leftPost = 0;
                }
                keyCount--;
            }
        }

        List<Post> recList = new LinkedList<>();
        ListIterator<Post> itr = searchedList.listIterator();
        for(int i = 0 ; i < 10; i++){
            if(itr.hasNext()){
                recList.add(itr.next());
            }
        }

        return recList;
    }



}

class PostComp implements Comparator<Post>{
    public int compare(Post p1, Post p2){
        LocalDateTime d1 = LocalDateTime.parse(p1.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalDateTime d2 = LocalDateTime.parse(p2.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        if(d1.isBefore(d2)){
            return 1;
        } else if(d1.isAfter(d2)){
            return -1;
        } else{
            return 0;
        }
    }
}
