package com.Richard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by su7163if on 3/22/2017.
 */
public class CardValidator extends JFrame {
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton ValidateButton;
    private JButton quitButton;
    private JLabel validMessageLabel;

    protected CardValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ValidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();

                boolean valid = isValidCreditCardNumber(ccNumber);
                if (valid) {
                    validMessageLabel.setText("Credit card number is VALID");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    protected static boolean isValidCreditCardNumber(String cardNumber) {
        //Putting the card number into a char array.
        char[] numArray = cardNumber.toCharArray();
        //Since we can assume that the first number on the card is 4, card total will start at 8.
        int cardTotal = 8;
        int digitCheck = 0;
        //Assumes card number is not valid.
        boolean visaCard = false;

        //if the number provided is longer than 16 digits method returns false.
        if (cardNumber.length() > 16) {
            System.out.println("Invalid card number.");
            return visaCard;
        }
        //Since visa numbers always start with 4 it checks to see if that's true with the card provided.
        if (Character.getNumericValue(numArray[0]) == 4) {
            //Every other number after 4 is multiplied by 2.
            for (int x = 2; x <= 14; x += 2) {
                digitCheck = Character.getNumericValue(numArray[x]) * 2;
                //if the sum is greater than 10...
                if (digitCheck >= 10) {
                    //...each digit is separated out...
                    String decaString = Integer.toString(digitCheck);
                    char[] decaArray = decaString.toCharArray();
                    //..and then added together and put into the total.
                    cardTotal += Character.getNumericValue(decaArray[0]) + Character.getNumericValue(decaArray[1]);
                } else {
                    //if digitCheck is less than to it is just added to total.
                    cardTotal += digitCheck;
                }
            }
            //This is adds the rest of the numbers that aren't multiplied by 2.
            for (int x = 1; x <= 15; x += 2) {
                cardTotal += Character.getNumericValue(numArray[x]);
            }
        }

        double visaConfirm = cardTotal % 10;
        if (visaConfirm == 0) {
            visaCard = true;
            return visaCard;
        } else {
            return visaCard;

        }
    }
}
