package com.example.buet_edu_project_one_vmasum.Answer;

import android.os.AsyncTask;

import com.example.buet_edu_project_one_vmasum.Graph.GraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BoardMatcher extends AsyncTask<Void, Void, Boolean> {
    private AnswerDialog dialog;
    private GraphView graphView;
    private JSONArray solutions;

    public BoardMatcher(AnswerDialog dialog, GraphView graphView, JSONArray solutions) {
        this.dialog = dialog;
        this.graphView = graphView;
        this.solutions = solutions;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        int i;

        // Match All Solutions
        for (i=0; i<solutions.length(); i++) {
            try {
                JSONObject solution = solutions.getJSONObject(i);
                if (graphView.match(solution)) break;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // If iterated Through all answers, no solutions found
        return i!=solutions.length();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        dialog.showDialog(aBoolean);
    }
}
