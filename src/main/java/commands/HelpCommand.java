package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = CommandEnum.HELP.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n" + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        List<String> commandNames = new ArrayList<>();
        commandNames.add(AddDeadlineCommand.MESSAGE_USAGE);
        commandNames.add(AddEventCommand.MESSAGE_USAGE);
        commandNames.add(AddToDoCommand.MESSAGE_USAGE);
        commandNames.add(DeleteTaskCommand.MESSAGE_USAGE);
        commandNames.add(DoneTaskCommand.MESSAGE_USAGE);
        commandNames.add(SearchTasksByDateCommand.MESSAGE_USAGE);
        commandNames.add(ListCommand.MESSAGE_USAGE);
        commandNames.add(HelpCommand.MESSAGE_USAGE);
        commandNames.add(ExitCommand.MESSAGE_USAGE);
        ui.showCommands(commandNames);
    }
}
