package parser;

import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public static final Pattern ADD_DEADLINE_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<deadlineDescription>.*) ((\\/by) (?<deadlineDate>.*))"); // variable number of tags

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
            case FIND:
                return doFindTaskBySpecificDate(arguments);
            case BYE:
                return new ExitCommand();
            default:
                return new IncorrectCommand("Unable to execute command");

        }
    }

    private static Command doListCommand(){
        return new ListCommand();
    }

    private static Command doAddToDoCommand(String args){
        try{
            return new AddToDoCommand(new Todo(args));
        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    private static Command doAddDeadlineCommand(String args){
        try{
            final Matcher matcher = ADD_DEADLINE_DATA_ARGS_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String deadlineDescription = matcher.group("deadlineDescription").trim();
            String deadlineDate = matcher.group("deadlineDate").trim();

            Task task = new Deadline(deadlineDescription, parseStringDateFromText(deadlineDate));
            return new AddDeadlineCommand(task);

        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    private static Command doAddEventCommand(String args){
        try{
            final Matcher matcher = ADD_EVENT_DATA_ARGS_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String eventDescription = matcher.group("eventDescription").trim();
            String eventDate = matcher.group("eventDate").trim();

            Task task = new Event(eventDescription, parseStringDateFromText(eventDate));
            return new AddDeadlineCommand(task);

        }catch(Exception e){
            return new IncorrectCommand("Task cannot be added. No description details");
        }
    }

    private static Command doDoneTaskCommand(String args){
        try{
            int targetIndex = Integer.parseInt((args));
            return new DoneTaskCommand(targetIndex - 1);
        } catch (Exception e){
            return new IncorrectCommand("Task cannot be mark as done!");
        }
    }

    private static Command doDeleteTaskCommand(String args){
        try{
            int targetIndex = Integer.parseInt((args));
            return new DeleteTaskCommand(targetIndex - 1);
        } catch (Exception e){
            return new IncorrectCommand("Task cannot be deleted");
        }
    }

    private static Command doFindTaskBySpecificDate(String args){
        try{
            LocalDate dt = parseStringFindDateFromText(args);
            return new FindTasksByDateCommand(dt);
        } catch (Exception e){
            return new IncorrectCommand("Find Task by specific date encountered exception");
        }
    }

    /**
     * Parses a String date into LocalDate object
     * Example: (2021-10-15 to Oct 15 2021)
     *
     * @param dateTime String
     * @return Converted String date into a LocalDate object
     */
    public static LocalDate parseStringFindDateFromText (String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate d = LocalDate.parse(dateTime, formatter);
        return d;
    }

    public static LocalDateTime parseStringDateFromText (String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime dateTimeResult = LocalDateTime.parse(dateTime, formatter);
        return dateTimeResult;
    }

    /**
     * Parses a LocalDateTime object into meaningful date String format MMM dd yyyy.
     * Example: (2021-10-15 to Oct 15 2021)
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
     * Example: (2021-10-15 600PM to 15/12/2021 1800)
     *
     * @param dateTime LocalDateTime object
     * @return Converted LocalDateTime into String Date format
     */
    public static String parseDateForStorage (LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }





}
