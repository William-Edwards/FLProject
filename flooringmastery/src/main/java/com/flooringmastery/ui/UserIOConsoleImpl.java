package com.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    final private Scanner console = new Scanner(System.in);

    // some code from ClassRoster code along

    /**
     *
     * @param msg - String of information to display to the user.
     *
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    /**
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    /**
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the
     *         min/max range
     */
    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a long
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as long
     */
    @Override
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the
     * console,
     * and continually reprompts the user with that message until they enter a
     * double
     * within the specified min/max range to be returned as the answer to that
     * message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an long value as an answer to the message prompt within the min/max
     *         range
     */
    @Override
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a float
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as float
     */
    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console,
     * and continually reprompts the user with that message until they enter a float
     * within the specified min/max range to be returned as the answer to that
     * message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an float value as an answer to the message prompt within the min/max
     *         range
     */
    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a
     * double
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as double
     */
    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console,
     * and continually reprompts the user with that message until they enter a
     * double
     * within the specified min/max range to be returned as the answer to that
     * message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the min/max
     *         range
     */
    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a
     * BigDecimal
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as BigDecimal
     */

    @Override
    public BigDecimal readBigDecimal(String msgPrompt) {
        while (true) {
            try {

                // may need to set scale and precision
                return new BigDecimal(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the
     * console,
     * and continually reprompts the user with that message until they enter a
     * BigDecimal
     * within the specified min/max range to be returned as the answer to that
     * message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an BigDecimal value as an answer to the message prompt within the
     *         min/max
     *         range
     */

    @Override
    public BigDecimal readBigDecimal(String msgPrompt, BigDecimal min, BigDecimal max) {
        BigDecimal result;
        do {
            result = readBigDecimal(msgPrompt);
        } while (result.compareTo(min) < 0 || result.compareTo(max) > 0);

        return result;
    }

}
