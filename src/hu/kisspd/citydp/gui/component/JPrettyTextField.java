package hu.kisspd.citydp.gui.component;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JPrettyTextField extends JTextField {
    private Shape shape;
    private int arcSize = 15;

    private String placeholder;

    public JPrettyTextField() {
        super();
        setOpaque(false);
    }

    public JPrettyTextField(String text) {
        super(text);
        setOpaque(false);
    }

    public JPrettyTextField(final int columns) {
        super(columns);
        setOpaque(false);
    }

    public JPrettyTextField(final String text, final int columns) {
        super(text, columns);
        setOpaque(false);
    }

    public JPrettyTextField(final Document doc, final String text, final int columns) {
        super(doc, text, columns);
        setOpaque(false);
    }

    public int getArcSize() {
        return arcSize;
    }

    public void setArcSize(int arcSize) {
        this.arcSize = arcSize;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // draw the rounded background
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);

        super.paintComponent(g);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        // draw the placeholder text
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getDisabledTextColor());
        g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // draw the rounded border
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
    }

    @Override
    public boolean contains(int x, int y) {
        // check if the click was inside the rounded area (shape)
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
        }
        return shape.contains(x, y);
    }
}
