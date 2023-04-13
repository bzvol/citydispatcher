package hu.kisspd.citydp.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class JPrettyButton extends JButton {
    private Shape shape;
    private int arcSize = 15;
    private final MouseListener hoverListener = new MouseListener() {
        private Color c;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            c = getBackground();
            int red = Math.max(0, c.getRed() - 10);
            int green = Math.max(0, c.getGreen() - 10);
            int blue = Math.max(0, c.getBlue() - 10);
            setBackground(new Color(red, green, blue));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(c);
        }
    };

    public JPrettyButton() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(hoverListener);
    }

    public JPrettyButton(Icon icon) {
        super(icon);
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(hoverListener);
    }

    public JPrettyButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(hoverListener);
    }

    public JPrettyButton(Action a) {
        super(a);
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(hoverListener);
    }

    public JPrettyButton(String text, Icon icon) {
        super(text, icon);
        setOpaque(false);
        setContentAreaFilled(false);
        addMouseListener(hoverListener);
    }

    public int getArcSize() {
        return arcSize;
    }

    public void setArcSize(int arcSize) {
        this.arcSize = arcSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // draw the rounded background
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
        // TODO: not working

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // draw the rounded border
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcSize, arcSize);
        }
        return shape.contains(x, y);
    }
}
