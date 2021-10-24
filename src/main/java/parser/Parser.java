package parser;

import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.*;
import data.Deadline;
import data.Event;
import data.Task;
import data.Todo;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("\\b(todo)\\b (?<todoDetails>.*)|\\b(deadline|event)\\b (?<deadlineEventDetails>.*) (\\/(?<deadlineEventDate>.*))"); // variable number of tags

    /**
     * Used for initial separation of deadline description and deadline date.
     */
    public static final Pattern ADD_DEADLINE_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<deadlineDescription>.*) ((\\/by) (?<deadlineDate>.*))"); // variable number of tags

    /**
     * Used for initial separation of event description and event date.
     */
    public static final Pattern ADD_EVENT_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<eventDescription>.*) ((\\/at) (?<eventDate>.*))"); // variable number of tags

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput){
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        CommandEnum commandEnum = null;
        // Match with Command Enums
        final String commandWord = matcher.group("commandWord").toUpperCase().trim();
        final String arguments = matcher.group("arguments").trim();

        try{
            commandEnum = CommandEnum.valueOf(commandWord);
        } catch (IllegalArgumentException e){
            return new IncorrectCommand(("Incorrect Command: "+commandWord));
        }
        switch (commandEnum) {
        case LIST:
            return doListCommand();
        case TODO:
            return doAddToDoCommand(arguments);
        case DEADLINE:
            return doAddDeadlineCommand(arguments);
        case EVENT:
            return doAddEventCommand(arguments);
        case DELETE:
            return doDeleteTaskCommand(arguments);
        case DONE:
            return doDoneTaskCommand(arguments);
        case SEARCH:
            return doSearchTaskBySpecificDate(arguments);
        case FIND:
            return doFindTaskByKeyword(arguments);
        case HELP:
            return doHelpCommand();
        case BYE:
            return new ExitCommand();
        default:
            return new IncorrectCommand("Unable to execute command");

        }
    }
    /**
     * Perform list command.
     *
     * @return the prepared command
     */
    private static Command doListCommand(){
        return new ListCommand();
    }

    /**
     * Perform help command.
     *
     * @return the prepared command
     */
    private static Command doHelpCommand() { return new HelpCommand();}

    /**
     * Parses arguments in the context of the add task [todo] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doAddToDoCommand(String args){
        try{
            return new AddToDoCommand(new Todo(args));
        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    /**
     * Parses arguments in the context of the add task [deadline] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doAddDeadlineCommand(String args){
        try{
            final Matcher matcher = ADD_DEADLINE_DATA_ARGS_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String deadlineDescription = matcher.group("deadlineDescription").trim();
            String deadlineDate = matcher.group("deadlineDate").trim();

            Task task = new Deadline(deadlineDescription, parseStringDateTimeFromText(deadlineDate));
            return new AddDeadlineCommand(task);

        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    /**
     * Parses arguments in the context of the add task [event] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doAddEventCommand(String args){
        try{
            final Matcher matcher = ADD_EVENT_DATA_ARGS_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String eventDescription = matcher.group("eventDescription").trim();
            String eventDate = matcher.group("eventDate").trim();

            Task task = new Event(eventDescription, parseStringDateTimeFromText(eventDate));
            return new AddEventCommand(task);

        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    /**
     * Parses arguments in the context of the mark as completed task [done] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doDoneTaskCommand(String args){
        try{
            int targetIndex = Integer.parseInt((args));
            return new DoneTaskCommand(targetIndex - 1);
        } catch (Exception e){
            return new IncorrectCommand("Task cannot be mark as done!");
        }
    }

    /**
     * Parses arguments in the context of the [delete] task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doDeleteTaskCommand(String args){
        try{
            int targetIndex = Integer.parseInt((args));
            return new DeleteTaskCommand(targetIndex - 1);
        } catch (Exception e){
            return new IncorrectCommand("Task cannot be deleted");
        }
    }

    /**
     * Parses arguments in the context of the search task by specific date [search] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doSearchTaskBySpecificDate(String args){
        try{
            LocalDate dt = parseStringFindDateFromText(args);
            return new SearchTasksByDateCommand(dt);
        } catch (Exception e){
            return new IncorrectCommand("Search Task by specific date encountered exception");
        }
    }

    /**
     * Parses arguments in the context of the find task by keyword [find] command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command doFindTaskByKeyword(String args){
        try{
            String[] keywords = args.split(" ");
            Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
            return new FindTaskByKeywordCommand(keywordSet);
        } catch (Exception e){
            return new IncorrectCommand("Find Task by keyword encountered exception");
        }
    }

    /**
     * Parses a String date of format d/MM/yyyy into LocalDate object
     * Example: (2/10/2021 to 2021-10-2)
     *
     * @param date String
     * @return Converted String date into a LocalDate object
     */
    public static LocalDate parseStringFindDateFromText (String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return d;
    }

    /**
     * Parses a String date of format d/MM/yyyy HHMM into LocalDateTime object
     * Example: (2/10/2021 0800 to 2021-10-2T08:00)
     * Used in TaskListDecoder to decode task.txt tasks in TaskList with LocalDateTime attribute
     *
     * @param dateTime String
     * @return Converted String date into a LocalDate object
     */
    public static LocalDateTime parseStringDateTimeFromText (String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime dateTimeResult = LocalDateTime.parse(dateTime, formatter);
        return dateTimeResult;
    }

    /**
     * Parses a LocalDateTime object into meaningful date String format MMM dd yyyy.
     * Example: (2021-10-15T08:00 to Oct 15 2021)
     *
     * @param dateTime LocalDateTime object
     * @return Converted LocalDateTime into String Date format
     */
    public static String parseDateForDisplay (LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
        return dateTime.format(formatter);
    }

    /**
     * Parses a LocalDateTime object into meaningful date String format d/MM/yyyy HHmm.
     * Example: (2021-10-15T06:00 to 15/10/2021 0600)
     * Used in TaskListEncoder to encode the Tasks in the TaskList to the specified format of task.txt.
     *
     * @param dateTime LocalDateTime object
     * @return Converted LocalDateTime into String Date format
     */
    public static String parseDateForStorage (LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }





}
