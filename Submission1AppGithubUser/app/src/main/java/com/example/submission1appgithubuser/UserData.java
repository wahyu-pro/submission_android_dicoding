package com.example.submission1appgithubuser;

import java.util.ArrayList;

public class UserData {
    private static String[] userNames = {
            "JakeWharton",
            "amitshekhariitbhu",
            "romainguy",
            "chrisbanes",
            "tipsy",
            "ravi8x",
            "jasoet",
            "budioktaviyan",
            "hendisantika",
            "sidiqpermana"
    };

    private static String[] name = {
            "Jake Wharton",
            "AMIT SHEKHAR",
            "Romain Guy",
            "Chris Banes",
            "David",
            "Ravi Tamada",
            "Deny Prasetyo",
            "Budi Oktaviyan",
            "Hendi Santika",
            "Sidiq Permana"
    };


    private static String[] company = {
            "Google, Inc.",
            "@MindOrksOpenSource",
            "Google",
            "@google working on @android",
            "Working Group Two",
            "AndroidHive | Droid5",
            "@gojek-engineering",
            "@KotlinID",
            "@JVMDeveloperID @KotlinID @IDDevOps",
            "Nusantara Beta Studio"
    };

    private static String[] location = {
            "Pittsburgh, PA, USA",
            "New Delhi, India",
            "California",
            "Sydney, Australia",
            "Trondheim, Norway",
            "India",
            "Kotagede, Yogyakarta, Indonesia",
            "Jakarta, Indonesia",
            "Bojongsoang - Bandung Jawa Barat",
            "Jakarta Indonesia"
    };

    private static String[] repository = {
            "102",
            "37",
            "9",
            "30",
            "56",
            "28",
            "44",
            "110",
            "1064",
            "65"

    };

    private static String[] follower = {
            "56995",
            "5153",
            "7972",
            "14725",
            "788",
            "18628",
            "277",
            "178",
            "428",
            "465"
    };

    private static String[] following = {
            "12",
            "2",
            "0",
            "1",
            "0",
            "3",
            "39",
            "23",
            "61",
            "10"
    };

    private static int[] userImages = {
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4,
            R.drawable.user5,
            R.drawable.user6,
            R.drawable.user7,
            R.drawable.user8,
            R.drawable.user9,
            R.drawable.user10
    };

    static ArrayList<User> getListData() {
        ArrayList<User> list = new ArrayList<>();
        for (int position = 0; position < userNames.length; position++) {
            User user = new User();
            user.setName(name[position]);
            user.setPhoto(userImages[position]);
            user.setUsername(userNames[position]);
            user.setCompany(company[position]);
            user.setLocation(location[position]);
            user.setRepository(repository[position]);
            user.setFollower(follower[position]);
            user.setFollowing(following[position]);
            list.add(user);
        }
        return list;
    }
}
