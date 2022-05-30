package tg.project.ui.bot.constants.action;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActionCommands {

    public static final String ANY = null;
    public static final String START = "/start";
    public static final String MENU = "/menu";
    public static final String SUBSCRIBE = "/subscribe";
    public static final String UNSUBSCRIBE = "/unsubscribe";
    public static final String UNSUBSCRIBE_ALL = "/unsubscribeall";
    public static final String SHOW_SUBSCRIBES = "/showsubscribes";

    public static final List<String> LIST = Arrays.asList(
            START, MENU, SUBSCRIBE, UNSUBSCRIBE, UNSUBSCRIBE_ALL, SHOW_SUBSCRIBES
    );
}