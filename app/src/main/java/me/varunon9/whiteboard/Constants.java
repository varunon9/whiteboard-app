package me.varunon9.whiteboard;

/**
 * Created by varunkumar on 11/6/18.
 */

public class Constants {
    public static int NOTIFICATION_ID = 101;
    public static String NOTIFICATION_TITLE = "Your Whiteboard";
    public static String NOTIFICATION_TEXT = "You can edit content anytime.";
    public static String CHANNEL_ID = "CHANNEL_ID";

    public interface Actions {
        String MAIN_ACTION = "me.varunon9.whiteboard.action.main";
        String STARTFOREGROUND_ACTION = "me.varunon9.whiteboard.action.startforeground";
        String STOPFOREGROUND_ACTION = "me.varunon9.whiteboard.action.stopforeground";
        String EDIT_TODO_ACTION = "me.varunon9.whiteboard.action.edittodoaction";
    }
}
