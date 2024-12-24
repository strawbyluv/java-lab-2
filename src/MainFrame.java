import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainFrame extends JFrame
{
    private Formula sum =new Formula();

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldSum;

    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    private JPanel imagePane;

    public Double calculate1(Double x, Double y, Double z)
    {
        return Math.pow((Math.cos(Math.exp(y)) + Math.exp(Math.pow(y, 2)) + Math.sqrt(1 / x)), 0.25) /
                Math.pow((Math.cos(Math.PI * Math.pow(z, 3)) + Math.log(Math.pow(1 + z, 2))), Math.sin(y));
    }

    public Double calculate2(Double x, Double y, Double z)
    {
        return ((1 + Math.pow(x, z) + Math.log(Math.pow(y, 2))) * (1 - Math.sin(y * z))) /
                (Math.sqrt(Math.pow(x, 3) + 1));
    }

    private void addRadioButton(String buttonName, final int formulaId)
    {
        JRadioButton button = new JRadioButton(buttonName);

        // определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {

                MainFrame.this.formulaId = formulaId;
                //imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);

        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        JLabel labelForResult = new JLabel("Результат:");


        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Вычислить");
        // источник события, регистрация обработчика, обработчик события
        buttonCalc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                try
                {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x,y,z);
                    else
                        result = calculate2(x,y,z);
                    textFieldResult.setText(result.toString());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        hboxResult.add(Box.createHorizontalGlue());
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();

        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);

        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                try
                {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x,y,z)+sum.getSum();
                    else
                        result = calculate2(x,y,z)+sum.getSum();
                    sum.setSum(+result);
                    textFieldSum.setText(result.toString());
                    //textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldSum.setText("0");
                sum.setSum(0);
            }
        });
        Box hboxButtonsMMC = Box.createHorizontalBox();
        hboxButtonsMMC.add(Box.createHorizontalGlue());
        hboxButtonsMMC.add(buttonM);
        hboxButtonsMMC.add(Box.createHorizontalStrut(30));
        hboxButtonsMMC.add(buttonMC);
        hboxButtonsMMC.add(Box.createHorizontalGlue());
        hboxButtonsMMC.setBorder(
                BorderFactory.createLineBorder(Color.ORANGE));

        JLabel labelForSum = new JLabel("Cумма:");
//labelResult = new JLabel("0");
        textFieldSum = new JTextField("0", 25);
        textFieldSum.setMaximumSize(
                textFieldSum.getPreferredSize());
        Box hboxSum = Box.createHorizontalBox();
        hboxSum.add(Box.createHorizontalGlue());
        hboxSum.add(labelForSum);
        hboxSum.add(Box.createHorizontalStrut(10));
        hboxSum.add(textFieldSum);
        hboxSum.add(Box.createHorizontalGlue());
        hboxSum.setBorder(BorderFactory.createLineBorder(Color.PINK));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxSum);
        contentBox.add(hboxButtons);
        contentBox.add(hboxButtonsMMC);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
