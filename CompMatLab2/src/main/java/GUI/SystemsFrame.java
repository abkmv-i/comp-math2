package GUI;

import DefaultData.DefaultSystemsOfEquations;
import GUI.FileInput.Parser;
import SolutionsData.SystemData;
import GUI.Interfaces.DataEntryFrame;
import SolutionMethods.SystemSolvingMethods.NewtonMethod;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.Map;

import static javax.swing.GroupLayout.Alignment.*;

public class SystemsFrame extends JFrame implements DataEntryFrame {
    private static final ComponentFactory tool = ComponentFactory.getInstance();

    private final SystemData data = new SystemData();

    JPanel radioButtonsWrapper = new JPanel();

    private final ButtonGroup group = new ButtonGroup();

    private JPanel buttons;

    private static final String DEFAULT_METHOD = "Метод Ньютона";

    private static final String HEADER_TEXT = "Решение систем нелинейных уравнений";

    private final JTextField bordersField = new JTextField("a;b");

    private final JTextField accuracyField = new JTextField("x>0");

    private static final NewtonMethod SOLUTION_METHOD = new NewtonMethod();

    private final JFrame parent;

    public final JFrame frame = this;

    public SystemsFrame(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void Frame() {
        setTitle(TITLE);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        createFilling();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel createSolvedObjectChoiceComponent() {
        JPanel wrapper = new JPanel();

        radioButtonsWrapper.setLayout(new BoxLayout(radioButtonsWrapper, BoxLayout.Y_AXIS));
        radioButtonsWrapper.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JLabel methodsChoiceTitle = new JLabel("Выберите систему уравнений:");

        JPanel firstRadioButtonWrapper = tool.createRadioButton(DefaultSystemsOfEquations.getFirstSystem());
        JPanel secondRadioButtonWrapper = tool.createRadioButton(DefaultSystemsOfEquations.getSecondSystem());

        group.add((JRadioButton) firstRadioButtonWrapper.getComponent(0));
        group.add((JRadioButton) secondRadioButtonWrapper.getComponent(0));

        ((JRadioButton) firstRadioButtonWrapper.getComponent(0)).setSelected(true);

        radioButtonsWrapper.add(firstRadioButtonWrapper);
        radioButtonsWrapper.add(secondRadioButtonWrapper);

        wrapper.add(methodsChoiceTitle);
        wrapper.add(radioButtonsWrapper);

        return wrapper;
    }

    @Override
    public JPanel createFields() {
        JPanel mainWrapper = new JPanel();

        GroupLayout fieldsLayout = new GroupLayout(mainWrapper);
        mainWrapper.setLayout(fieldsLayout);
        fieldsLayout.setAutoCreateGaps(true);
        fieldsLayout.setAutoCreateContainerGaps(true);

        JLabel methodTitle = new JLabel("Метод решения:");
        JLabel bordersTitle = new JLabel("Введите границы начального приближения:");
        JLabel accuracyTitle = new JLabel("Введите точность:");

        JLabel defaultMethod = new JLabel(DEFAULT_METHOD);

        bordersField.setName("borders");
        accuracyField.setName("accuracy");

        JLabel errorBorderLabel = new JLabel("");
        errorBorderLabel.setForeground(Color.RED);
        errorBorderLabel.setVisible(false);
        ValidateBorder validateBorder = new ValidateBorder(errorBorderLabel);

        JLabel errorAccuracyLabel = new JLabel("");
        errorAccuracyLabel.setForeground(Color.RED);
        errorAccuracyLabel.setVisible(false);
        ValidateAccuracy validateAccuracy = new ValidateAccuracy(errorAccuracyLabel);

        bordersField.setInputVerifier(validateBorder);
        accuracyField.setInputVerifier(validateAccuracy);

        fieldsLayout.setHorizontalGroup(fieldsLayout.createParallelGroup(CENTER)
                .addGroup(fieldsLayout.createSequentialGroup()
                        .addGroup(fieldsLayout.createParallelGroup(LEADING)
                                .addComponent(methodTitle)
                                .addComponent(bordersTitle)
                                .addComponent(accuracyTitle)
                        )
                        .addGroup(fieldsLayout.createParallelGroup(LEADING)
                                .addComponent(defaultMethod)
                                .addComponent(bordersField)
                                .addComponent(accuracyField)
                        ))
                .addComponent(errorBorderLabel)
                .addComponent(errorAccuracyLabel)
        );

        fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(methodTitle)
                        .addComponent(defaultMethod))
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(bordersTitle)
                        .addComponent(bordersField))
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(accuracyTitle)
                        .addComponent(accuracyField))
                .addComponent(errorBorderLabel)
                .addComponent(errorAccuracyLabel)
        );

        return mainWrapper;
    }

    @Override
    public JPanel createContent() {
        JPanel content = new JPanel();

        content.add(createFields());
        content.add(createSolvedObjectChoiceComponent());

        return content;
    }

    @Override
    public void createFilling() {
        getContentPane().add(createHeader(HEADER_TEXT), BorderLayout.NORTH);
        getContentPane().add(createContent(), BorderLayout.CENTER);
        buttons = tool.createControlButtons(new ButtonPressListener());
        getContentPane().add(buttons, BorderLayout.BEFORE_FIRST_LINE);
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

    private File chooseFile() {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only csv files", "csv");

        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int ret = chooser.showDialog(null, "Выбрать файл");

        File file = null;

        if (ret == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }

        chooser.setVisible(true);

        return file;
    }

    private Map[] getSystem() {
        int numberOfSelectedButton = 0;
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                break;
            }
            numberOfSelectedButton++;
        }
        return DefaultSystemsOfEquations.getMap().get(numberOfSelectedButton);
    }

    private String getEquationName() {
        int numberOfSelectedButton = 0;
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                break;
            }
            numberOfSelectedButton++;
        }
        switch (numberOfSelectedButton) {
            case 0 -> {
                return "FIRST_SYSTEM";
            }
            case 1 -> {
                return "SECOND_SYSTEM";
            }
            case 2 -> {
                return "THIRD_SYSTEM";
            }
            default -> {
                return null;
            }
        }
    }

    private String[] getEquation() {
        int numberOfSelectedButton = 0;
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                break;
            }
            numberOfSelectedButton++;
        }
        switch (numberOfSelectedButton) {
            case 0 -> {
                return new String[] {
                        DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationView,
                        DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationView
                };
            }
            case 1 -> {
                return new String[] {
                        DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationView,
                        DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationView
                };
            }
            default -> {
                return null;
            }
        }
    }

    private class ButtonPressListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "file" -> {
                    Parser parser = new Parser(chooseFile());
                    String[] parseData = parser.parseData();
                    bordersField.setText(parseData[0].trim() + ";" + parseData[1].trim());
                    accuracyField.setText(parseData[2].trim());
                    ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(true);
                }
                case "calculate" -> {
                    data.setSystemName(getEquationName());
                    data.setSystemView(getEquation());
                    data.setLeftBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[0]));
                    data.setRightBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[1]));
                    data.setAccuracy(Double.parseDouble(accuracyField.getText().replace(",", ".")));
                    data.setSystem(getSystem());
                    SOLUTION_METHOD.setData(data);
                    String[] solution = SOLUTION_METHOD.iterationsCycle();
                    SolutionFrame solutionFrame = new SolutionFrame(data, frame);
                    solutionFrame.setSolution(solution);
                    solutionFrame.Frame();
                    dispose();
                }
                case "back" -> {
                    dispose();
                    parent.setVisible(true);
                }
                default -> {
                }
            }
        }
    }

    private class ValidateBorder extends InputVerifier {

        private final JLabel errorLabel;

        private static final String ERROR_MESSAGE = "Введено неверное значение границы";

        public ValidateBorder(JLabel errorLabel) {
            this.errorLabel = errorLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText();
            if (text != null && !text.trim().equals("")) {
                String[] values = text.replace(",", ".").split(";");
                double rightBorder;
                double leftBorder;
                try {
                    rightBorder = Double.parseDouble(values[0]);
                    leftBorder = Double.parseDouble(values[1]);
                } catch (NumberFormatException e) {
                    ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(false);
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (rightBorder != leftBorder) {
                    errorLabel.setVisible(false);
                }
                if (accuracyField.isValidateRoot()) {
                    ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(true);
                }
                return true;
            }
            ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(false);
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }

    private class ValidateAccuracy extends InputVerifier {

        private final JLabel errorLabel;

        private static final String ERROR_MESSAGE = "Введено неверное значение точности";

        public ValidateAccuracy(JLabel errorLabel) {
            this.errorLabel = errorLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText();
            if (text != null && !text.trim().equals("")) {
                double value;
                try {
                    value = Double.parseDouble(text.replace(",", "."));
                } catch (NumberFormatException e) {
                    ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(false);
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (value >= 0) {
                    errorLabel.setVisible(false);
                }
                if (bordersField.isValidateRoot()) {
                    ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(true);
                }
                return true;
            }
            ((JPanel) buttons.getComponent(0)).getComponent(1).setEnabled(false);
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }
}
