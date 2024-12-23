/* ============================================================
 * ENVISIONZ : A Data Visualization Tool for large sets of data
 * ============================================================
 * 
 * (C)opyright 2018-2020, by Dirk Zeitz.  All rights reserved.
 * 
 * dirk.zeitz@yahoo.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please contact the author
 * 
 * 
 */
package gui.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly
*/

public class GuiGlassPane extends JPanel implements KeyListener, MouseListener, MouseMotionListener, FocusListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -1944534829802701569L;

	private JMenuBar menuBar;

	private Container contentPane;

	private boolean inDrag = false;

	private boolean needToRedispatch = false;

	private GuiProgressPanel progressPanel;

	public GuiGlassPane(JMenuBar mb, Container cp){
		super();
		this.setLayout(new GridBagLayout());
		this.setRequestFocusEnabled(true);
		this.setDoubleBuffered(true);
	    menuBar = mb;
	    contentPane = cp;
	    addKeyListener(this);
	    addMouseListener(this);
	    addMouseMotionListener(this);
	    addFocusListener(this);

	    setBackground(Color.WHITE);
        setFont(new Font("Default", Font.BOLD, 16));

        progressPanel = new GuiProgressPanel();
        progressPanel.setOpaque(false);
        
        this.removeAll();
        this.add(progressPanel);
   	}

	public JMenuBar getMenubar() { return this.menuBar; }
	public Container getContentPane() { return this.contentPane; }
	
	public void setProgress(int value){
		this.progressPanel.updateProgressBar(value);
	}

	public void setProgressText(String text){
		this.progressPanel.updateTextField(text);
	}
	
	public GuiProgressPanel getProgressPanel(){
		return this.progressPanel;
	}

	public void setVisible(boolean v) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    if (v){
	    	//requestFocus();
	    	this.grabFocus();
	    }
	    super.setVisible(v);
	}

	public void keyTyped(KeyEvent ke) {
		ke.consume();
	}
	public void keyReleased(KeyEvent ke) {
		ke.consume();
	}
	public void keyPressed(KeyEvent ke) {
		ke.consume();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (isVisible()){
			requestFocus();
		}
	}

	public void setNeedToRedispatch(boolean need) {
		needToRedispatch = need;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (needToRedispatch){
			redispatchMouseEvent(e);
		}
	}

	private void redispatchMouseEvent(MouseEvent e) {
		boolean inButton = false;
		boolean inMenuBar = false;
		Point glassPanePoint = e.getPoint();
		Component component = null;
		Container container = contentPane;
		Point containerPoint = SwingUtilities.convertPoint(this, glassPanePoint, contentPane);
		int eventID = e.getID();

		if (containerPoint.y < 0) {
			inMenuBar = true;
			container = menuBar;
			containerPoint = SwingUtilities.convertPoint(this, glassPanePoint, menuBar);
			testForDrag(eventID);
		}

		component = SwingUtilities.getDeepestComponentAt(container, containerPoint.x, containerPoint.y);

		if (component == null) {
			return;
		} else {
			inButton = true;
			testForDrag(eventID);
		}

		if (inMenuBar || inButton || inDrag) {
			Point componentPoint = SwingUtilities.convertPoint(this, glassPanePoint, component);
			component.dispatchEvent(new MouseEvent(component, eventID, e.getWhen(), e.getModifiers(), componentPoint.x, componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
		}
	}

	private void testForDrag(int eventID) {
		if (eventID == MouseEvent.MOUSE_PRESSED) {
			inDrag = true;
		}
	}

    @Override
    protected void paintComponent(Graphics g) {
    	//System.out.println("paintComponent");
        // enables anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // gets the current clipping area
        Rectangle clip = g.getClipBounds();

        // sets a 65% translucent composite
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.65f);
        Composite composite = g2.getComposite();
        g2.setComposite(alpha);

        // fills the background
        g2.setColor(getBackground());
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);

        // centers the progress bar on screen
        /*FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - BAR_WIDTH) / 2;
        int y = (getHeight() - BAR_HEIGHT - metrics.getDescent()) / 2;

        // draws the text
        g2.setColor(TEXT_COLOR);
        g2.drawString(message, x, y);

        // goes to the position of the progress bar
        y += metrics.getDescent();

        // computes the size of the progress indicator
        int w = (int) (BAR_WIDTH * ((float) progress / 100.0f));
        int h = BAR_HEIGHT;

        // draws the content of the progress bar
        Paint paint = g2.getPaint();

        // bar's background
        Paint gradient = new GradientPaint(x, y, GRADIENT_COLOR1,
                x, y + h, GRADIENT_COLOR2);
        g2.setPaint(gradient);
        g2.fillRect(x, y, BAR_WIDTH, BAR_HEIGHT);

        // actual progress
        gradient = new LinearGradientPaint(x, y, x, y + h,
                GRADIENT_FRACTIONS, GRADIENT_COLORS);
        g2.setPaint(gradient);
        g2.fillRect(x, y, w, h);

        g2.setPaint(paint);

        // draws the progress bar border
        g2.drawRect(x, y, BAR_WIDTH, BAR_HEIGHT);*/

        g2.setComposite(composite);
    }

}
