package de.westlb.mgb.client.ui.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** 
 *  The SDateSelectorDialog shown below, combines a {@link SDateSelector} and
 *  a {@link Popup_dialog} to provide a standalone, popup dialog
 *  for choosing dates.

 *  The class does implement the {@link SDateSelector} interface, but
 *  bear in mind that the window closes when the user selects a date.
 *  Unlike the {@link SDateSelector} class, both of the action events are sent 
 *  to listeners, however.
 *
 * This class is a stand-alone dialog. For a version
 * that you can embed into another window, see {@link SDateSelectorPanel}.
 *
 */

public class SDateSelectorDialog extends JDialog implements SDateSelector
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4685050689495809984L;
	private SDateSelector selector;

	/** Creates a dialog box with the indicated parent that holds
	 *  a standard {@link SDateSelectorPanel SDateSelectorPanel}
	 *  (as created using the no-arg constructor).
	 */
	public SDateSelectorDialog( Frame parent )
	{	super(parent);
		setModal(true);
		selector = new SDateSelectorPanel();
		init();
	}

	/** 
	 * Like {@link #SDateSelectorDialog(Frame),
	 * but for a {@link Dialog} parent.
	 */
	public SDateSelectorDialog( Dialog parent )
	{	super(parent);
		setModal(true);
		selector = new SDateSelectorPanel();
		init();
	}

	/** Creates a dialog box with the indicated parent that holds
	 *  the indicated SDateSelector.
	 *  Note that the current month and year is displayed in the
	 *  dialog-box title bar, so there's no need to display it in
	 *  the selector too.
	 */
	public SDateSelectorDialog( Frame parent, SDateSelector to_wrap )
	{	super(parent);
		selector = to_wrap;
		init();
	}

	/** Like {@link #Date_selector_dialog(Frame,Date_selector),
	 * but for a {@link Dialog} parent.
	 */

	public SDateSelectorDialog( Dialog parent, SDateSelector to_wrap )
	{	super(parent);
		selector = to_wrap;
		init();
	}
	
	/** Code comon to all constructors
	 */
	private void init()	{	
		getContentPane().add( (Container)selector, BorderLayout.CENTER );
		selector.addActionListener
		(	new ActionListener()
			{	@Override
            public void actionPerformed( ActionEvent event )
				{	if( event.getID() == SDateSelector.CHANGE_ACTION )
					{	setTitle( event.getActionCommand() );
					}
					else
					{	setVisible(false);
						dispose();
					}
				}
			}
		);
		((Container)selector).setVisible(true);
		pack();
	}

	/** For use when you pop up a dialog using 
	 * <code>setVisible(true)</code> rather than {@link #select}.
	 * @return the selected date or null if the dialog was closed
	 * 			without selecting anything.
	 */
	@Override
    public Calendar getSelectedDate()
	{	return selector.getSelectedDate();
	}

	/** Get the current date. The dialog stays in existance
	 *  until the user closes it or selects a date, so this
	 *  method can be used to see what month the user has
	 *  scrolled to.
	 *  @return the date currently displayed on the calendar.
	 */
	@Override
    public Calendar getCurrentDate()
	{	return selector.getCurrentDate();
	}

	/** Add an action listner for both 
	 *  {@link Date_selector#CHANGE_ACTION} and
	 *  {@link Date_selector#SELECT_ACTION} action events.
	 */
    @Override
    public void addActionListener(ActionListener l)
	{	selector.addActionListener(l);
	}

	/** Remove a previously-added listener */
    @Override
    public void removeActionListener(ActionListener l)
	{	selector.removeActionListener(l);
	}

	/** Pops up the chooser and blocks until the user selects
	 *  a date.
	 * 
	 * @return the selected date or null if the dialog was closed
	 * 			without selecting anything.
	 */
	public Calendar select()
	{	
		setVisible(true);
		return selector.getSelectedDate();
	}

	@Override
    public void roll(int f, boolean up)	{        selector.roll(f,up);		}
	@Override
    public int  get(int f)				{ return selector.get(f);			}

	//----------------------------------------------------------------------
	@SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception	{
		final JFrame frame = new JFrame();
		frame.getContentPane().add( new JLabel("Main Frame") );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.show();

		SDateSelectorDialog chooser = new SDateSelectorDialog(frame);
		chooser.setLocation(10,10);
		System.out.println("Displaying Selector");

		System.out.println(chooser.select());

		// No navigation bar
		chooser = new SDateSelectorDialog(frame,
								new SDateSelectorPanel(1900,1,2));

		chooser.setLocation(10,10);
		System.out.println("Displaying Selector");

		System.out.println(chooser.select());
	}
}
