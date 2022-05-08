package com.example.renju;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderBoard {

    private static LeaderBoard instance;

    private static final String PREF = "pref";
    private static final String LEADERBOARD_KEY = "leaderboard key";
    SharedPreferences sharedPreferences;
    Map<String, Integer> leaderBoardMap;
    private Gson gson = new Gson();



    private LeaderBoard(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String leaderBoardMapJson = sharedPreferences.getString(LEADERBOARD_KEY, null);
        if (leaderBoardMapJson == null) leaderBoardMap = new HashMap<>();
        else leaderBoardMap = gson.fromJson(leaderBoardMapJson, new TypeToken<Map<String, Integer>>() {}.getType());
    }

    public static LeaderBoard getInstance(Context context) {
        if (instance == null) instance = new LeaderBoard(context);
        return instance;
    }

    private void addPlayer(String playerName) {
        leaderBoardMap.put(playerName, 0);
    }

    public void playerWon(String playerName) {
        Integer score = leaderBoardMap.get(playerName);

        if (score == null) {
            addPlayer(playerName);
            score = 0;
        }

        leaderBoardMap.put(playerName, score + 1);
        save();
    }

    private void save() {
        String leaderBoardMapJson = gson.toJson(leaderBoardMap);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LEADERBOARD_KEY, leaderBoardMapJson);
        editor.apply();
    }

    public List<String> getLeaderBoardList() {
        List<String> result = new ArrayList<>();
        for (String name: leaderBoardMap.keySet()) {
            result.add(name + "\n" + leaderBoardMap.get(name));
        }
        return result;
    }





}
