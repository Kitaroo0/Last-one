import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.*;
import static java.lang.Integer.parseInt;

public class ContactForm extends JFrame {

    JTextField name_field, email_field, surname_field, iin_field, password_field, con_field;
//    JRadioButton male, female;
//    JCheckBox check;

    public ContactForm(){

        super("Registration");
        super.setBounds(200, 100, 300,230);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(6, 2, 2,10));

        JLabel name = new JLabel("Write your name");
        name_field = new JTextField("", 1);
        JLabel surname = new JLabel("Write your surname");
        surname_field = new JTextField("", 1);
        JLabel email = new JLabel("Write your email");
        email_field = new JTextField("@", 1);
        JLabel password = new JLabel("Write your password:");
        password_field = new JTextField("", 1);
        JLabel iin = new JLabel("Write your iin:");
        iin_field = new JTextField("", 1);

        container.add(name);
        container.add(name_field);
        container.add(surname);
        container.add(surname_field);
        container.add(email);
        container.add(email_field);
        container.add(password);
        container.add(password_field);
        container.add(iin);
        container.add(iin_field);


        JButton send_button = new JButton("Send");

        container.add(send_button);

        send_button.addActionListener(new ButtonEventManager());
    }

    class ButtonEventManager implements ActionListener{

//        private Object label;

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = name_field.getText();
            String email = email_field.getText();
            String surname = surname_field.getText();


            int iin = Integer.parseInt(iin_field.getText());
            int password = Integer.parseInt(password_field.getText());
            if(password < 100000){
                JOptionPane.showMessageDialog(null, "The password must consist of 6 characters");
            }else if(iin < 1000000000){
                JOptionPane.showMessageDialog(null, "The IIN must consist of 12 digits.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "Name: " + name + "Surname: " + surname + "Password: " + password + "IIN: " + iin,
                        "Hello " + name, JOptionPane.PLAIN_MESSAGE);
            }

//                String isMale = "Male";
//                if(!male.isSelected()){
//                    isMale = "Female";
//                }
//
//                boolean checkbox = check.isSelected();


//            JOptionPane.showMessageDialog(null, "Name: " + name +
//                        "\nSurname: " + surname + "\nEmail: " + email
//                    + "\nPassword" + password
//                    + "\nIIN" + iin,
//                    "Привет," + name, JOptionPane.PLAIN_MESSAGE);

        }

    }
}