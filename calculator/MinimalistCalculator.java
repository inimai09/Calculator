package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinimalistCalculator implements ActionListener {

    JTextField textField;
    double num1 = 0, num2 = 0;
    char operator;
    JButton[] numberButton = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, delButton, clrButton, equalButton, decButton;

    MinimalistCalculator() {
        JFrame frame = new JFrame("iPhone Style Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        textField = new JTextField();
        textField.setBounds(20, 30, 320, 70);
        textField.setEditable(false);
        textField.setFont(new Font("Helvetica Neue", Font.PLAIN, 40));
        textField.setBackground(new Color(0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(textField);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        equalButton = new JButton("=");
        decButton = new JButton(".");
        delButton = new JButton("DEL");
        clrButton = new JButton("C");

        JButton[] functionButtons = {addButton, subButton, mulButton, divButton, delButton, clrButton, equalButton, decButton};

        Color bgDark = new Color(20, 20, 20);
        Color numGray = new Color(51, 51, 51);
        Color operatorOrange = new Color(255, 149, 0);
        Color lightGray = new Color(165, 165, 165);
        Color green = new Color(48, 209, 88);
        Color red = new Color(255, 69, 58);

        for (JButton btn : functionButtons) {
            btn.addActionListener(this);
            btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
            btn.setFocusable(false);
            btn.setBorder(null);
            btn.setOpaque(true);
            btn.setForeground(Color.WHITE);
        }

        addButton.setBackground(operatorOrange);
        subButton.setBackground(operatorOrange);
        mulButton.setBackground(operatorOrange);
        divButton.setBackground(operatorOrange);
        equalButton.setBackground(operatorOrange);
        decButton.setBackground(numGray);
        delButton.setBackground(red);
        clrButton.setBackground(red);

        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
            numberButton[i].setFocusable(false);
            numberButton[i].setBorder(null);
            numberButton[i].setBackground(numGray);
            numberButton[i].setForeground(Color.WHITE);
            numberButton[i].setOpaque(true);
        }

        delButton.setBounds(20, 400, 150, 50);
        clrButton.setBounds(190, 400, 150, 50);
        frame.add(delButton);
        frame.add(clrButton);

        JPanel panel = new JPanel();
        panel.setBounds(20, 120, 320, 260);
        panel.setLayout(new GridLayout(4, 4, 8, 8));
        panel.setBackground(bgDark);

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);

        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);

        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);

        panel.add(decButton);
        panel.add(numberButton[0]);
        panel.add(equalButton);
        panel.add(divButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MinimalistCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButton[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        if (e.getSource() == decButton && !textField.getText().contains(".")) {
            textField.setText(textField.getText() + ".");
        }

        if (e.getSource() == addButton) {
            num1 = getNumber();
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = getNumber();
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = getNumber();
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = getNumber();
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equalButton) {
            num2 = getNumber();
            switch (operator) {
                case '+': textField.setText(String.valueOf(num1 + num2)); break;
                case '-': textField.setText(String.valueOf(num1 - num2)); break;
                case '*': textField.setText(String.valueOf(num1 * num2)); break;
                case '/':
                    if (num2 != 0) textField.setText(String.valueOf(num1 / num2));
                    else textField.setText("Error");
                    break;
            }
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
        }

        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (text.length() > 0) textField.setText(text.substring(0, text.length() - 1));
        }
    }

    private double getNumber() {
        String text = textField.getText();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }
}
