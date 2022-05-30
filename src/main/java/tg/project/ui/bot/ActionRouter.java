package tg.project.ui.bot;

import tg.project.ui.bot.constants.action.ActionCommands;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActionRouter {

    private final Map<String, Action> driveMap = new HashMap<>();

    public void put(String key, Action value) {
        driveMap.put(key, value);
    }

    public Action get(String key) {
        return driveMap.getOrDefault(key, driveMap.get(ActionCommands.ANY));
    }
}