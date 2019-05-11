package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixSettingsPanel extends JPanel {
    private MatrixPanel _panel;
    private int _xSize = 250;
    private int _ySize = 250;

    private JTextField rowsText = new JTextField(6);
    private JTextField colsText = new JTextField(6);
    private JButton resetButton = new JButton("RESET");

    public MatrixSettingsPanel(MatrixPanel panel){
        _panel = panel;
        this.setPreferredSize(new Dimension(_xSize, _ySize));
        Draw();

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rows = Integer.parseInt(rowsText.getText());
                    int cols = Integer.parseInt(colsText.getText());

                    if(rows > 100 || cols > 100 || rows < 1 || cols < 1)
                        JOptionPane.showMessageDialog(null, "Stop trying to break it!\n"
                                + "[You weren't really going to use a " + rows + "x"
                                + cols + " matrix]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                    else
                        _panel.ResetMatrix(rows, cols);
                }
                catch (Exception err){
                    JOptionPane.showMessageDialog(null, "You just had to go and break our stuff...\n"
                            + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void Draw(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.insets = new Insets(5,5,5,5);
        gc.anchor = GridBagConstraints.EAST;
        gc.gridx = 0; gc.gridy = 0;
        this.add(new JLabel("Rows  "),gc);

        gc.gridx = 0; gc.gridy = 1;
        this.add(new JLabel("Cols  "),gc);

        gc.gridx = 1; gc.gridy = 0;
        rowsText.setText(Integer.toString(_panel.GetMatrix().rows()));
        this.add(rowsText,gc);

        gc.gridx = 1; gc.gridy = 1;
        colsText.setText(Integer.toString(_panel.GetMatrix().cols()));
        this.add(colsText,gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 2;
        gc.gridx = 0; gc.gridy = 2;
        this.add(resetButton,gc);
    }
}

