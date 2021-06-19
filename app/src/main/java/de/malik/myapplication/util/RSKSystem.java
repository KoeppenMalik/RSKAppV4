// Created on 23.01.2021, 15:27

package de.malik.myapplication.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.Main;
import de.malik.myapplication.gui.fragments.ErrorFragment;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.filemanagement.FileManager;
import de.malik.myapplication.util.filter.Filter;
import de.malik.myapplication.util.filter.FilterValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class RSKSystem {

    public static final int NO_ANIM = -1;

    private Filter[] filters;
    private Filter currentFilter = new Filter("ID", FilterValue.ID);
    private Main main;
    private ProjectManager projectManager;
    private FileManager fileManager;
    private Project recentlyVisitedProject = null;

    public RSKSystem(Main main, ProjectManager projectManager, FileManager fileManager) {
        this.main = main;
        this.projectManager = projectManager;
        this.fileManager = fileManager;
        filters = new Filter[] {
                new Filter("NEU ZU ALT", FilterValue.NEW_TO_OLD),
                new Filter("ID", FilterValue.ID)
        };
    }

    public Main getMain() {
        return main;
    }

    /**
     * replaces the current layout with a new one
     * @param fragment the fragment which should be displayed
     */
    public void replaceCurrentFragmentWith(Fragment fragment, @AnimatorRes @AnimRes int anim) {
        if (anim == NO_ANIM) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, fragment).commit();
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(anim, 0).replace(R.id.containerLayout, fragment).commit();
    }

    /**
     * creates a Toast with length: Toast.LENGTH_SHORT
     * @param text the text that will be displayed
     */
    public void makeShortToast(String text) {
        Toast.makeText(main, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * creates a string which contains all ids of the given arraylist
     * @param pauses an arraylist of the class pause which contains all the ids
     * @return a string which contains all ids of the given arraylist. the ids are splitted by the current split regex
     */

    public static String getSplittedIds(ArrayList<Pause> pauses) {
        String pauseIds = "";

        for (Pause pause : pauses) {
            pauseIds += FileManager.SPLIT_REGEX + pause.getId();
        }
        return pauseIds;
    }

    /**
     *
     * @return the main activity context
     */
    public Context getContext() {
        return main.getApplicationContext();
    }

    /**
     *
     * @return the support fragment manager of the main activity
     */
    public FragmentManager getSupportFragmentManager() {
        return main.getSupportFragmentManager();
    }

    /**
     *
     * @return the search view of the main activity
     */
    public SearchView getMainActivitySearchView() {
        return main.getSearchView();
    }

    /**
     * shows a fragment with the given error message
     * @param message the error message
     */
    public void showErrorDialog(String message) {
        replaceCurrentFragmentWith(new ErrorFragment(message, this), android.R.anim.fade_in);
    }

    public Filter[] getFilters() {
        return filters;
    }

    public Filter getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(Filter currentFilter) {
        this.currentFilter = currentFilter;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setRecentlyVisitedProject(Project recentlyVisitedProject) {
        this.recentlyVisitedProject = recentlyVisitedProject;
    }

    public Project getRecentlyVisitedProject() {
        return recentlyVisitedProject;
    }

// --------------------------------------------------------------- new Class ---------------------------------------------------------------

    public static class TimeManager {

        public static final String DATE_FORMAT = "dd.MM.yyyy";
        public static final String[] WEEK_DAYS = new String[] {
                "", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
        };

        /**
         * splits the argument time by ':' and parses it to a new instance of the class Time
         * @param time the time which will be parsed
         * @return a new Time instance
         */
        public static Time parseTime(String time) {
            String[] values = time.split(":");
            if (values[1].contains("Uhr")) {
                values[1] = values[1].split(" ")[0];
            }
            return new Time(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        }

        /**
         * returns the the difference between the start time and the end time
         * @param start start time
         * @param end end time
         * @param pauses An Arraylist of pauses. Add one if they should be included
         * @return a new instance of the class Time with the time difference
         */
        public static Time getDiffInMinutes(Time start, Time end, @Nullable ArrayList<Pause> pauses) {
            final int DIFF = Time.convert(ConversionFactor.MINUTES, end) - Time.convert(ConversionFactor.MINUTES, start);
            int pauseTimes = 0;

            if (pauses != null) {
                for (Pause pause : pauses) {
                    pauseTimes += Time.convert(ConversionFactor.MINUTES, pause.getTime());
                }
            }
            int totalTimeDiff = DIFF - pauseTimes;
            return new Time((totalTimeDiff / 60), totalTimeDiff % 60);
        }

        /**
         * checks if the given String is an integer
         * @param arg the String which will be checked if it is an integer
         * @return true if the given String is an integer otherwise false
         */
        public static boolean isInteger(String arg) {
            if (arg.isEmpty()) {
                return false;
            }
            for (char c : arg.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * the input strings have to be in the format: 'hour:minute Uhr' for example '14:30 Uhr'. it splits the String by ' '.
         * The result is 'hour:minute' or for example '14:30'
         * @param args the String which will be splitted
         * @return an Array of String which contains all the formatted times
         */
        public static String[] parseTimes(String... args) {
            String[] dates = new String[args.length];

            for (int i = 0; i < args.length; i++) {
                dates[i] = args[i].split(" ")[0];
            }
            return dates;
        }

        /**
         * finds out the current date and formats it to 'dd.MM.yyyy'
         * @return the current date
         */
        public static String getCurrentDate() {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
            return formatter.format(new Date());
        }
    }
}
