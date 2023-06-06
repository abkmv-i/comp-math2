package GUI;

import GUI.Interfaces.DataEntryFrame;
import GUI.Interfaces.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JDialog implements Frame {

    private DataEntryFrame childFrame;

    private final JFrame parent = new JFrame(TITLE);

    private static final String HEADER_TEXT = "Выберите:";

    @Override
    public void Frame() {
        parent.setTitle(TITLE);
        parent.setIconImage(icon.getImage());
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setResizable(false);
        parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
        createFilling();
        parent.pack();
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);

    }

    private JPanel createChoiceButtons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 5, 15));

        ActionListener buttonPressListener = new ButtonPressListener();

        JButton equationMenuRedirect = new JButton("Нелинейные уравнения");
        equationMenuRedirect.setActionCommand("equation");

        JButton systemMenuRedirect = new JButton("Системы нелинейных уравнений");
        systemMenuRedirect.setActionCommand("system");

        equationMenuRedirect.addActionListener(buttonPressListener);
        systemMenuRedirect.addActionListener(buttonPressListener);

        buttonsPanel.add(equationMenuRedirect);
        buttonsPanel.add(systemMenuRedirect);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(buttonsPanel);

        return flow;
    }

    @Override
     public JPanel createHeader(String headerText) {
        JPanel headerPanel = new JPanel();

        JLabel header =  new JLabel(headerText);

        header.setFont(HEADER_FONT);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(header);
        return headerPanel;
    }

    @Override
    public void createFilling() {
         parent.getContentPane().add(createHeader(HEADER_TEXT), BorderLayout.NORTH);
         parent.getContentPane().add(createChoiceButtons(), BorderLayout.CENTER);
    }

    private class ButtonPressListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "system":
                    childFrame = new SystemsFrame(parent);
                    break;
                case "equation":
                    childFrame = new EquationsFrame(parent);
                    break;
                default:
                    break;
            }
            childFrame.Frame();
            parent.setVisible(false);
        }
    }

}
