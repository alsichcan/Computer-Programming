package server;

import course.Bidding;
import course.Course;
import utils.Config;
import utils.ErrorCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class User {
    String userID;
    List<Bidding> biddingList;
    int totalMileage;
    String root;
    String bidDir;
    String conDir;

    public User(String userID, List<Bidding> biddingList) {
        this.userID = userID;
        this.biddingList = biddingList;
        this.root = "data/Users/" + userID;
        this.bidDir = root + "/bid.txt";
        this.conDir = root + "/reg.txt";

        this.totalMileage = 0;
        for (Bidding bid : biddingList) {
            totalMileage += bid.mileage;
        }
    }

    public int getTotalMileage() {
        return totalMileage;
    }

    public int bid(int courseId, int mileage) {
        int updateResult = updateBid(courseId, mileage);
        if(updateResult == ErrorCode.NEGATIVE_MILEAGE) return ErrorCode.NEGATIVE_MILEAGE;
        if(updateResult == ErrorCode.OVER_MAX_COURSE_MILEAGE) return ErrorCode.OVER_MAX_COURSE_MILEAGE;
        if(updateResult == ErrorCode.OVER_MAX_MILEAGE) return ErrorCode.OVER_MAX_MILEAGE;

        try {
            File bidInfo = new File(bidDir);
            FileWriter fw = new FileWriter(bidInfo, false);

            for (int i = 0; i < biddingList.size(); i++) {
                String data = biddingList.get(i).courseId + "|" + biddingList.get(i).mileage;
                fw.write(data);
                if (i != biddingList.size() - 1) fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ErrorCode.IO_ERROR;
        }
        return ErrorCode.SUCCESS;
    }


    public int updateBid(int courseId, int mileage){
        if(mileage < 0) return ErrorCode.NEGATIVE_MILEAGE;
        if(mileage > Config.MAX_MILEAGE_PER_COURSE) return ErrorCode.OVER_MAX_COURSE_MILEAGE;

        boolean prevBid = false;
        for(Bidding bid : biddingList){
            if(bid.courseId == courseId){
                prevBid = true;
                if(mileage == 0){
                    totalMileage -= bid.mileage;
                    biddingList.remove(bid);
                } else {
                    if(totalMileage - bid.mileage + mileage > Config.MAX_MILEAGE) return ErrorCode.OVER_MAX_MILEAGE;
                    totalMileage = totalMileage - bid.mileage + mileage;
                    bid.mileage = mileage;
                }
                break;
            }
        }
        if(!prevBid){
            if(totalMileage + mileage > Config.MAX_MILEAGE) return ErrorCode.OVER_MAX_MILEAGE;

            if(mileage != 0){
                totalMileage += mileage;
                biddingList.add(new Bidding(courseId, mileage));
            }
        }
        return ErrorCode.SUCCESS;
    }

    public void confirm(Course course) throws IOException{
        File conList = new File(conDir);
        FileWriter fw = new FileWriter(conList, true);
        fw.write(String.valueOf(course.courseId));
        fw.write("\n");
        fw.close();
    }

    public void emptyBid() throws IOException{
        this.biddingList = new LinkedList<>();
        FileWriter fw = new FileWriter(new File(bidDir), false);
        fw.write("");
        fw.close();
    }
}
