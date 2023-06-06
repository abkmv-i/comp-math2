package GUI;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComponentFactory {

    private static ComponentFactory instance = null;

    public static ComponentFactory getInstance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    public JPanel createRadioButton(String equation) {
        JRadioButton button = new JRadioButton();

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonWrapper.add(button);
        buttonWrapper.add(new JLabel(getTeXIcon(equation)));

        return buttonWrapper;
    }

    public Icon getTeXIcon(String formula) {
        TeXFormula texFormula = new TeXFormula(formula);
        return texFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 10);
    }

    public JPanel createControlButtons(ActionListener buttonsListener) {
        JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 0));

        JButton fileInput = new JButton("Ввести из файла");
        fileInput.setActionCommand("file");

        JButton calculateButton = new JButton("Вычислить");
        calculateButton.setActionCommand("calculate");
        calculateButton.setEnabled(false);

        JButton backButton = new JButton("Назад");
        backButton.setActionCommand("back");

        fileInput.addActionListener(buttonsListener);
        calculateButton.addActionListener(buttonsListener);
        backButton.addActionListener(buttonsListener);

        buttons.add(fileInput);
        buttons.add(calculateButton);
        buttons.add(backButton);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(buttons);

        return flow;
    }

}
