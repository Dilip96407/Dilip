package de.westlb.mgb.client.ui.util;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

/** 
 *  A calendar-dispaly/date-selection widget.
 * 
 *  Parts of this class are copied from the example described by
 *  at http://java.arcadevillage.com/applets/ccalenda.htm
 * 
 *	"Today" is highlighted.
 *	Select a date by clicking on it.
 *	The background is transparant by default &mdash; it's grey here because
 *	the underlying window is grey.
 *	<p>
 	<img src="../../../images/Navigable_date_selector.gif">
 *  This "raw" date selector can be "decorated" in several
 *  ways to make it more useful.
 *  First, you can add a navigation bar to the bottom
 *	to advances the
 *	calandar by one month (single arrow) or one year (double arrow)
 *	forwards (right-pointing arrow)	or backwards (left-pointing arrow).
 *	"Today" is highlighted.
 *	Navigation bars are specified using a Gang-of-Four "Decorator"
 *	object that wraps the raw <code>Date_selector_panel</code>
 *	Both the wrapper and the underlying panel implement the
 *	<code>Date_selectory</code> interface, so can be use
 *	used interchangably. The following code creates the
 *	date selector at right.
 *	<pre>
 *	Date_selector selector = new Date_selector_panel();
 *	selector = new Navigable_date_selector( selector );
 *	</pre>
 *	The same thing can be accomplished with a convenience constuctor that
 *	creates the wrapped Date_selector_panel for you:
 *	<pre>
 *	Date_selector selector = new Navigable_date_selector();
 *	</pre>
 *	<p>
 *	<img src="../../../images/Titled_navigable_date_selector.gif">
 *	The other augmentation of interest is a title that shows the
 *  month name and year that's displayed. (there's an example at right).
 *  Use the same decoration strategy as before to add the title:
 *	<pre>
 *	Date_selector selector = new Date_selector_panel();
 *	selector = new Navigable_date_selector( selector );
 *	selector = new Titled_date_selector   ( selector );
 *	</pre>
 *	You can leave out the navigation bar by ommiting the
 *	second line of the foregoing code.
 *	Again, a convenience constructor is provided to create a
 *	titled date selector (without the navigation bar) as follows:
 *	<pre>
 *	Date_selector selector = new Titled_date_selector();
 *	</pre>
 *	<p>
 *	<img src="../../../images/Date_selector_dialog.gif">
 *	The final variant is the lightweight popup dialog shown at right.
 *	It can be dragged around by the title bar (though dragging can
 *	be disabled) and closed by clicking on the "close" icon on the
 *	upper right. As before, use a decorator to manufacture a dialog:
 *	<pre>
 *	Date_selector selector = new Date_selector_panel();
 *	selector = new Navigable_date_selector( selector ); // add navigation
 *	selector = new Date_selector_dialog   ( selector );
 *	</pre>
 *	Note that you don't need a title because one is supplied for you
 *	in the dialog-box title bar. Also as before, a convenience
 *	constructor to create a navigable dialog box like the one at 
 *	right:
 *	<pre>
 *	Date_selector = new Date_selectcor_dialog();
 *	<pre>
 *	All the earlier examples create a claendar for the current
 *	month. Several methods are provided, below, to change the date
 *	in your program. For the most part, they work like simliar
 *	methods of the {@link Calendar} class.
 * <DL>
 * <DT><b>Known Problems</b>
 * <DD>
 * The month names are hard coded (in English). Future versions
 * will load these strings from a resource bundle. The week layout
 * (S M T W Th F Sa Su) is the default layout for the underlying
 * {@link Calendar}, which should change with Locale as appropriate.
 * This feature has not been tested, however.
 * </DD>
 * </DL>
 *
 */

public class SDateSelectorPanel extends JPanel implements SDateSelector {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1781362967079437013L;

	private Logger logger = Logger.getLogger(SDateSelectorPanel.class);

	private static final int DAYS_IN_WEEK = 7;	// days in a week
	private static final int MAX_WEEKS    = 6;	// maximum weeks in any month

	private static final Color LIGHT_YELLOW  = new Color(0xff, 0xff, 0xdd);
	private static final Color DARK_RED	     = new Color(0x99, 0x00, 0x00);

	private static final Color NAVIGATION_BACKGROUND = LIGHT_YELLOW;
	private static final Color HIGHLIGHT_COLOR = DARK_RED;
	
	private String[] months =
	{	"Jan","Feb", "Mar","Apr", "May","June",
		"July","Aug","Sept","Oct","Nov","Dec"
	};

	// Names of images files used for the navigator bar.
	private static final String 
		NEXT_YEAR		= "images/10px.red.arrow.right.double.gif",
		NEXT_MONTH		= "images/10px.red.arrow.right.gif",
		PREVIOUS_YEAR	= "images/10px.red.arrow.left.double.gif",
		PREVIOUS_MONTH	= "images/10px.red.arrow.left.gif";

	// These constants are used both to identify the button, and
	// as the button caption in the event that the appropriate
	// immage file can't be located.

	private static final String FORWARD_MONTH 	= ">"	,
								FORWARD_YEAR	= ">>"	,
								BACK_MONTH		= "<"	,
								BACK_YEAR		= "<<"	;


	private Calendar selected = null;

	private Calendar calendar = Calendar.getInstance();
	{	calendar.set( Calendar.HOUR_OF_DAY, 	0 );
		calendar.set( Calendar.MINUTE,	0 );
		calendar.set( Calendar.SECOND,	0 );
	}

	// The calendar that's displayed on the screen

	private final Calendar today = Calendar.getInstance();

	// An ActionListener that fields all events coming in from the
	// calendar
	//
	private final DayListener dayListener  = new DayListener();

	// "days" is not a two-dimensional array. I drop buttons into
	// a gridLayout and let the layout manager worry about
	// what goes where. The first buttion is the first day of the
	// first week on the grid, the 8th button is the first day of the
	// second week of the grid, and so forth.

	private JButton[] days = new JButton[ DAYS_IN_WEEK * MAX_WEEKS ];
	{	for( int i = 0; i < days.length; i++ )
		{
			JButton day = new JButton("--");
			days[i] = day;
			day.setBorder			(new EmptyBorder(1,2,1,2));
			day.setFocusPainted		(false);
			day.setActionCommand	("D");
			day.addActionListener	(dayListener);
			day.setOpaque			(false);
		}
	}
	

	private NavigationPanel navigationPanel = new NavigationPanel();
	private CalendarPanel calendarPanel = new CalendarPanel();
	private final JLabel title = new JLabel("xx");
	
	private boolean navigationEnabled = true;
	private boolean titleEnabled = true;


	/** Create a Date_selector representing the current date.
	 */
	public SDateSelectorPanel()	{	

		try {
			 initComponents();
			 updateLayout();
		 } catch (Exception ex) {
			 logger.error("Exception in View-Constuctor", ex );
		 }

		calendarPanel.update();
	}

	/** Create a Date_selector_panel for an arbitrary date.
	 *  @param initial_date Calendar will display this date. The specified
	 *  					date is highlighted as "today".
	 *  @see #Date_selector_panel(int,int,int)
	 */

	public SDateSelectorPanel(Calendar initial_date)
	{	this();
		if (initial_date != null) {
			calendar.setTime( initial_date.getTime() );
			today.	 setTime( initial_date.getTime() );
			calendarPanel.update();
		}
	}
	
	private void initComponents() {
		setBorder( null  );
		setOpaque( false );
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setOpaque		( true 										);
		title.setBackground	( NAVIGATION_BACKGROUND	);	
		title.setFont		( title.getFont().deriveFont( Font.BOLD )	);
		this.addActionListener(new TitleListener());
	}
	
	private void updateLayout() {
		removeAll();
		setLayout( new BorderLayout() );
		add(calendarPanel,  BorderLayout.CENTER );
		if (titleEnabled) {
			add(title, BorderLayout.NORTH);
		}
		if (navigationEnabled) {
			add(navigationPanel, BorderLayout.SOUTH);
		}
	}
	
	public void setNavigationEnabled(boolean navigationEnabled) {
		this.navigationEnabled = navigationEnabled;
		updateLayout();
	}

	public void setTitleEnabled(boolean titleEnabled) {
		this.titleEnabled = titleEnabled;
		updateLayout();
	}


	/** Create a Date_selector_panel for an arbitrary date.
	 * @param year the full year (e.g. 2003)
	 * @param month the month id (0=january, 1=feb, etc. [this is the
	 * 			convention supported by the other date classes])
	 * @param day the day of the month. This day will be highlighted
	 * 			as "today" on the displayed calendar. Use 0 to suppress
	 * 			the highlighting.
	 *  @see #Date_selector_panel(Date)
	 */

	public SDateSelectorPanel( int year, int month, int day )
	{	this();
		calendar.set(year,month,day);
		if( day != 0 )
			today.set(year,month,day);
		calendarPanel.update();
	}

	/************************************************************************
	 * List of observers.
	 */

	private ActionListener subscribers = null;

	/** Add a listener that's notified when the user scrolls the
	 *  selector or picks a date.
	 *  @see Date_selector
	 */
    @Override
    public synchronized void addActionListener(ActionListener l)
	{	subscribers = AWTEventMulticaster.add(subscribers, l);
    }

	/** Remove a listener.
	 *  @see Date_selector
	 */
    @Override
    public synchronized void removeActionListener(ActionListener l)	{	
    	subscribers = AWTEventMulticaster.remove(subscribers, l);
	}

	/** Notify the listeners of a scroll or select
	 */
	private void fire_ActionEvent( int id, String command )	{	
		if (subscribers != null) {
			 subscribers.actionPerformed(new ActionEvent(this, id, command) );
		}
	}
	
	private class TitleListener implements ActionListener {
		@Override
        public void actionPerformed( ActionEvent e ) {	
			if ( e.getID() == CHANGE_ACTION) {
				title.setText( e.getActionCommand() );
			}
		}
	}

	/***********************************************************************
	 * Handle clicks from the buttons that represent calendar days.
	 */
	private class DayListener implements ActionListener {	
		@Override
        public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("D")){	
				String text = ((JButton) e.getSource()).getText();

				if(text.length() > 0) { //  <=0 means click on blank square. Ignore.
					calendar.set
					(	calendar.get(Calendar.YEAR),	// Reset the calendar
						calendar.get(Calendar.MONTH),	// to be the choosen
						Integer.parseInt(text)			// date.
					);
					selected = calendar;
					fire_ActionEvent( SELECT_ACTION, selected.toString() );
				}
			}
		}
	}


	/** Create a naviagion button with an image appropriate to the caption.
	 *	The <code>caption</code> argument is used as the button's "action command."
	 *	This method is public only because it has to be. (It overrides a public
	 *	method.) Pretend it's not here.
	 */

	@Override
    public void addNotify()
	{	
		super.addNotify();
		int month = calendar.get(Calendar.MONTH);
		int year  = calendar.get(Calendar.YEAR);
		fire_ActionEvent( CHANGE_ACTION, months[month] + " " + year );
	}

	/**	Returns the {@link Date Date} selected by the user or null if
	 *  the window was closed without selecting a date. The returned
	 *  Date has hours, minutes, and seconds values of 0.
	 */

	@Override
    public Calendar getSelectedDate()
	{	return selected;
	}

	/** Returns the currently displayed {@link Date Date}.  */
	@Override
    public Calendar getCurrentDate()
	{	return calendar;
	}

	/** Works just like {@link Calendar#roll(int,boolean)}.  */
	@Override
    public void roll(int field, boolean up)
	{	calendar.roll(field,up);
		calendarPanel.update();
	}

	/** Works just like {@link Calendar#roll(int,int)}.  */
	public void roll(int field, int amount)
	{	calendar.roll(field,amount);
		calendarPanel.update();
	}

	/** Works just like {@link Calendar#set(int,int,int)} 
	 *	Sets "today" (which is higlighted) to the indicated day.
	 */
	public void set( int year, int month, int date )
	{	calendar.set(year,month,date);
		today.set(year,month,date);
		calendarPanel.update();
	}

	/** Works just like {@link Calendar#get(int)} */
	@Override
    public int get( int field )
	{	return calendar.get(field);
	}

	/** Works just like {@link Calendar#setTime(Date)},
	 *	Sets "today" (which is higlighted) to the indicated day.
	 */
	public void setTime( Date d )
	{	calendar.setTime(d);
		today.setTime(d);
		calendarPanel.update();
	}

	/** Works just like {@link Calendar#getTime} */
	public Date getTime( )
	{	return calendar.getTime();
	}

	/** Return a Calendar object that represents the currently-displayed
	 *  month and year. Modifying this object will not affect the
	 *  current panel.
	 *  @return a Calendar representing the panel's state.
	 */

	public Calendar getCalendar()
	{	Calendar c = Calendar.getInstance();
		c.setTime( calendar.getTime() );
		return c;
	}

	/** Change the display to match the indicated calendar. This Calendar
	 *  argument is used only to provide the new date/time information.
	 *  Modifying it after a call to the current method will not affect
	 *  the Date_selector_panel at all.
	 *	Sets "today" (which is higlighted) to the indicated day.
	 *  @param calendar A calendar positioned t the date to display.
	 */

	public void setFromCalendar(Calendar calendar)
	{	this.calendar.setTime( calendar.getTime() );
		today.setTime( calendar.getTime() );
		calendarPanel.update();
	}

	/** 
	 * Changes the background color of the navigation panel.
	 */

	public void changeNavigationBarColor( Color background_color )	{	
		if( background_color != null ) {
			navigationPanel.setBackground( background_color );
			title.setBackground( background_color );
		} else {
			navigationPanel.setOpaque(false);
		}
	}

	/** Handle clicks from the navigation bar buttons. */
	private class NavigationListener implements ActionListener	{	
		@Override
        public void actionPerformed(ActionEvent e) {
			String direction = e.getActionCommand();
			if (direction == FORWARD_YEAR ) {
				roll(Calendar.YEAR,true);
			} else if(direction == BACK_YEAR    ) {
				roll(Calendar.YEAR,false);
			} else if(direction == FORWARD_MONTH)	{
				roll(Calendar.MONTH,true);
				if( get(Calendar.MONTH) == Calendar.JANUARY )
					roll(Calendar.YEAR,true);
			} else if (direction == BACK_MONTH 	) {
				roll(Calendar.MONTH,false);
				if( get(Calendar.MONTH) == Calendar.DECEMBER )
					roll(Calendar.YEAR,false);
			} else {	
				logger.error("Unexpected direction");
			}
		}
	}


	private class NavigationPanel extends JPanel {	
		/**
		 * 
		 */
		private static final long serialVersionUID = -7297965760418275174L;
		/** Represents the navigation bar for the SDateSelectionPanel
		 */
		private final NavigationListener navigationListener = new NavigationListener();

		public NavigationPanel() {
			try {
				 initComponents();
				 initLayout();
				 initLabels();
			 } catch (Exception ex) {
				 logger.error("Exception in View-Constuctor", ex );
			 }
		}
		
		private void initComponents() {
		}
		
		private void initLayout() {			
			setLayout(new FlowLayout());
			setBorder( null );
			setBackground( NAVIGATION_BACKGROUND );	
			add( makeNavigationButton(BACK_YEAR	) );
			add( makeNavigationButton(BACK_MONTH	) );
			add( makeNavigationButton(FORWARD_MONTH) );
			add( makeNavigationButton(FORWARD_YEAR ) );
		}
		
		private void initLabels() {
		}
		
		private JButton makeNavigationButton(String caption)
		{
			ClassLoader loader = getClass().getClassLoader();
			URL	image =
				(FORWARD_YEAR.equals(caption)	)? loader.getResource(NEXT_YEAR):
				(BACK_YEAR.equals(caption)   	)? loader.getResource(PREVIOUS_YEAR):
				(FORWARD_MONTH.equals(caption)	)? loader.getResource(NEXT_MONTH):
										   loader.getResource(PREVIOUS_MONTH) ;

			JButton b = (image!=null) ? new JButton( new ImageIcon(image) )
									  : new JButton(caption)
									  ;
			b.setBorder(new EmptyBorder(0,4,0,4));
			b.setFocusPainted(false);
			b.setActionCommand(caption);
			b.addActionListener( navigationListener );
			b.setOpaque( false );
			return b;	
		}
	}

	private class CalendarPanel extends JPanel {	
		/**
         * 
         */
        private static final long serialVersionUID = -8159196227840329298L;

        /** Wraps the calendar panel of the SDataSelectionPanel
		 */

		public CalendarPanel() {
			try {
				 initComponents();
				 initLayout();
				 initLabels();
			 } catch (Exception ex) {
				 logger.error("Exception in View-Constuctor", ex );
			 }
		}
		
		private void initComponents() {
		}
		
		private void initLayout() {
			JPanel buttonPanel = new JPanel();			
			buttonPanel.setLayout(new GridLayout(MAX_WEEKS /*rows*/, DAYS_IN_WEEK /*columns*/ ));
			buttonPanel.setOpaque(false);
			buttonPanel.setBorder( BorderFactory.createEmptyBorder(5,3,0,1) );

			for( int i = 0; i < days.length; ++i ) {
				buttonPanel.add(days[i]);
			}

			setOpaque( false );
			setLayout( new BorderLayout() );
			add(buttonPanel, BorderLayout.CENTER);
		}
		
		private void initLabels() {
		}

		/** Redraw the buttons that comprise the calandar to display the current month */

		private void update()
		{
			setVisible(false);	// improves paint speed & reduces flicker

			clear_highlight();

			// The buttons that comprise the calendar are in a single
			// dimentioned array that was added to a 6x7 grid layout in
			// order. Because of the linear structure, it's easy to
			// lay out the calendar just by changing the labels on
			// the buttons. Here's the algorithm used below
			//
			// 	1) find out the offset to the first day of the month.
			// 	2) clear everything up to that offset
			// 	3) add the days of the month
			// 	4) clear everything else

			int month = calendar.get(Calendar.MONTH);
			int year  = calendar.get(Calendar.YEAR);

			fire_ActionEvent( CHANGE_ACTION, months[month] + " " + year );

			calendar.set( year, month, 1 ); // first day of the current month.

			int first_day_offset = calendar.get(Calendar.DAY_OF_WEEK);		/* 1 */

			int i = 0;
			while( i < first_day_offset-1 )									/* 2 */
				days[i++].setText("");

			int day_of_month = 1;
			for(; i < days.length; ++i )									/* 3 */
			{	
				// Can't get calendar.equals(today) to work, so do it manually

				if(	calendar.get(Calendar.MONTH)==today.get(Calendar.MONTH)
				&&	calendar.get(Calendar.YEAR )==today.get(Calendar.YEAR )
				&&	calendar.get(Calendar.DATE )==today.get(Calendar.DATE ) )
				{	highlight( days[i] );
				}

				days[i].setText( String.valueOf(day_of_month) );

				calendar.roll( Calendar.DATE, /*up=*/ true );	// forward one day

				day_of_month = calendar.get(Calendar.DATE);
				if( day_of_month == 1 )
					break;
			}

			// Note that we break out of the previous loop with i positioned
			// at the last day we added, thus the following ++ *must* be a
			// preincrement becasue we want to start clearing at the cell
			// after that.

			while( ++i < days.length )										/* 4 */
				days[i].setText("");

			setVisible(true);
		}

		//----------------------------------------------------------------------

		private JButton highlighted = null;

		private void clear_highlight()
		{	
			if( highlighted != null )
			{	highlighted.setBackground( Color.WHITE );
				highlighted.setForeground( Color.BLACK );
				highlighted.setOpaque(false);
				highlighted = null;
			}
		}

		private void highlight( JButton cell )
		{	
			highlighted = cell;
			cell.setBackground( HIGHLIGHT_COLOR );
			cell.setForeground( Color.WHITE );
			cell.setOpaque( true );
		}
	
		//----------------------------------------------------------------------
	}

		
	//----------------------------------------------------------------------
	@SuppressWarnings("deprecation")
    public static void main( String[] args ) {	
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().setLayout( new FlowLayout() );

		SDateSelector left 	 = new SDateSelectorPanel();
		SDateSelector center = new SDateSelectorPanel();
		((SDateSelectorPanel)center).setNavigationEnabled(false);
		
		SDateSelector right  = new SDateSelectorPanel(1900,1,2);

		// ((Navigable_date_selector)center).change_navigation_bar_color(null); // transparent

		ActionListener l =
			new ActionListener()
			{	@Override
            public void actionPerformed(ActionEvent e)
				{}
			};

		left.addActionListener	(l);
		center.addActionListener(l);
		right.addActionListener	(l);

		JPanel white = new JPanel();				// proove that it's transparent.
		white.setBackground(Color.WHITE);
		white.add( (JPanel)center );

		frame.getContentPane().add( (JPanel)left    );	// I hate these casts, but they're
		frame.getContentPane().add( 		white );	// mandated by the fact that
		frame.getContentPane().add( (JPanel)right    );	// Component is not an interface.

		frame.pack();
		frame.show();
	}
}
