package de.freese.addressbook.views;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.IProgressConstants;
import org.eclipse.ui.progress.UIJob;

import de.freese.addressbook.Activator;
import de.freese.addressbook.entities.Address;
import de.freese.addressbook.perspectives.EditorAreaWatcher;
import de.freese.addressbook.services.AddressBookServices;
import de.freese.addressbook.services.IAddressService;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view shows data obtained
 * from the model. The sample creates a dummy model on the fly, but a real implementation would
 * connect to the model available either in this or another plug-in (e.g. the workspace). The view
 * is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be presented in the view. Each
 * view can present the same model objects using different labels and icons, if needed.
 * Alternatively, a single label provider can be shared between views in order to ensure that
 * objects of the same type are presented in the same way everywhere.
 * <p>
 */

public class AddressList extends ViewPart
{
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "de.freese.addressbook.views.AddressList";

	/**
	 * 
	 */
	private Button buttonLaden = null;

	/**
	 * 
	 */
	private Label labelLaden = null;

	/**
	 * 
	 */
	private String orginalTitel = null;

	/**
	 * 
	 */
	private TableViewer tableViewer = null;

	/**
	 * The constructor.
	 */
	public AddressList()
	{
		super();
	}

	/**
	 * Create the actions.
	 */
	private void createActions()
	{
		// Create the actions
	}

	/**
	 * @return {@link Action}
	 */
	protected Action createAddressenGeladenAction()
	{
		return new Action()
		{
			/**
			 * @see org.eclipse.jface.action.Action#run()
			 */
			@Override
			public void run()
			{
				MessageDialog.openInformation(getSite().getShell(), "Job Status",
						"Addressen geladen");
			}
		};
	}

	/**
	 * @param parent {@link Composite}
	 * @param viewer {@link TableViewer}
	 */
	private void createColumns(final Composite parent, final TableViewer viewer)
	{
		TableColumnLayout tableColumnLayout = (TableColumnLayout) parent.getLayout();

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		tableColumnLayout.setColumnData(viewerColumn.getColumn(), new ColumnWeightData(33, 100));
		viewerColumn.getColumn().setText("Name");
		// viewerColumn.getColumn().setWidth(150);

		viewerColumn.setLabelProvider(new ColumnLabelProvider()
		{
			/**
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(final Object element)
			{
				Address address = (Address) element;

				return address.getName();
			}
		});

		viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		tableColumnLayout.setColumnData(viewerColumn.getColumn(), new ColumnWeightData(33, 100));
		viewerColumn.getColumn().setText("City");
		// viewerColumn.getColumn().setWidth(150);

		viewerColumn.setLabelProvider(new ColumnLabelProvider()
		{
			/**
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(final Object element)
			{
				Address address = (Address) element;

				return address.getCity();
			}
		});

		viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		tableColumnLayout.setColumnData(viewerColumn.getColumn(), new ColumnWeightData(33, 100));
		viewerColumn.getColumn().setText("Country");
		// viewerColumn.getColumn().setWidth(150);

		viewerColumn.setLabelProvider(new ColumnLabelProvider()
		{
			/**
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(final Object element)
			{
				Address address = (Address) element;

				return address.getCountry().getName();
			}
		});
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		getSite().getPage().addPartListener(new EditorAreaWatcher(getSite().getPage()));

		parent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new RowLayout(SWT.VERTICAL));

		this.labelLaden = new Label(composite, SWT.NONE);
		this.labelLaden.setText("Label");
		this.labelLaden.setFont(JFaceResources.getTextFont());

		// LocalResourceManager resources =
		// new LocalResourceManager(JFaceResources.getResources(), parent);
		// Font font = resources.createFont(FontDescriptor.createFrom("Arial", 10, SWT.BOLD));
		// this.labelLaden.setFont(font);
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.addPropertyChangeListener(new IPropertyChangeListener()
		{
			/**
			 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
			 */
			@Override
			public void propertyChange(final PropertyChangeEvent event)
			{
				System.out.println(event);
			}
		});

		this.buttonLaden = new Button(composite, SWT.NONE);
		this.buttonLaden.addSelectionListener(new SelectionAdapter()
		{
			/**
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(final SelectionEvent e)
			{
				getLabelLaden().setText("Keine Adressen");
				getLabelLaden().getParent().layout();

				getTableViewer().setInput(null);

				refresh();
			}
		});

		this.buttonLaden.setText("Addressen laden");

		createTableViewer(parent);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * @param parent {@link Composite}
	 */
	private void createTableViewer(final Composite parent)
	{
		Composite tableComposite = new Composite(parent, SWT.BORDER);
		tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);

		this.tableViewer = new TableViewer(tableComposite, SWT.NONE);

		createColumns(tableComposite, this.tableViewer);

		Table table = this.tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// Popup
		MenuManager menuManager = new MenuManager();
		table.setMenu(menuManager.createContextMenu(table));
		getSite().registerContextMenu("de.freese.somepopup", menuManager, this.tableViewer);

		this.tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		this.tableViewer.setComparator(new ViewerComparator()
		{
			/**
			 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
			 *      java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(final Viewer viewer, final Object e1, final Object e2)
			{
				Address address1 = (Address) e1;
				Address address2 = (Address) e2;

				int comp = address1.getName().compareTo(address2.getName());

				if (comp == 0)
				{
					comp = address1.getCity().compareTo(address2.getCity());
				}

				return comp;
			}
		});

		// Make the selection available to other views
		getSite().setSelectionProvider(this.tableViewer);

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		// gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		this.tableViewer.getControl().setLayoutData(gridData);
	}

	/**
	 * @return {@link Label}
	 */
	private Label getLabelLaden()
	{
		return this.labelLaden;
	}

	/**
	 * @return {@link TableViewer}
	 */
	private TableViewer getTableViewer()
	{
		return this.tableViewer;
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu()
	{
		// IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar()
	{
		// IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * @param job {@link Job}
	 * @return boolean
	 */
	public boolean isModal(final Job job)
	{
		Boolean isModal = (Boolean) job.getProperty(IProgressConstants.PROPERTY_IN_DIALOG);

		if (isModal == null)
		{
			return false;
		}

		return isModal.booleanValue();
	}

	/**
	 * 
	 */
	public void refresh()
	{
		Job job = new Job("Lade Addressen")
		{
			/**
			 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
			 */
			@Override
			protected IStatus run(final IProgressMonitor monitor)
			{
				showBusy(true);

				monitor.beginTask("", 100);

				IAddressService service = AddressBookServices.getAddressService();
				List<Address> allAddresses = service.getAllAddresses();

				monitor.subTask("Addressen geladen, Update UI");
				monitor.worked(50);

				try
				{
					Thread.sleep(3000);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

				setAddressList(allAddresses);
				monitor.worked(100);

				showBusy(false);

				// if (isModal(this))
				// {
				// // The progress dialog is still open so just open the message
				// showAddressenGeladen();
				// }
				// else
				// {
				// // setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
				// setProperty(IProgressConstants.ACTION_PROPERTY, createAddressenGeladenAction());
				// }

				return Status.OK_STATUS;
			}
		};

		// Dialog anzeigen.
		job.setUser(true);

		// Unsichtbar im Hintergrund.
		// job.setSystem(true);

		// Properties
		job.setProperty(IProgressConstants.ICON_PROPERTY, getTitleImage());
		job.setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
		// job.setProperty(IProgressConstants.PROPERTY_IN_DIALOG, Boolean.TRUE);
		// BusyIndicator.showWhile(getSite().getShell().getDisplay(), job);}
		job.schedule();
	}

	/**
	 * @param addresses {@link List}
	 */
	private void setAddressList(final List<Address> addresses)
	{
		UIJob uiJob = new UIJob("Update Addresses")
		{
			/**
			 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
			 */
			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor)
			{
				getLabelLaden().setText(addresses.size() + " Adressen geladen");

				// Weil LabelBounds sich geändert haben.
				getLabelLaden().getParent().layout();

				getTableViewer().setInput(addresses);
				getTableViewer().getTable().select(0);

				return Status.OK_STATUS;
			}
		};
		uiJob.schedule();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{
		this.buttonLaden.setFocus();
	}

	/**
	 * 
	 */
	protected void showAddressenGeladen()
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			/**
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run()
			{
				createAddressenGeladenAction().run();
			}
		});
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#showBusy(boolean)
	 */
	@Override
	public void showBusy(final boolean busy)
	{
		super.showBusy(busy);

		UIJob uiJob = new UIJob("Update Title")
		{

			/**
			 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
			 */
			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor)
			{
				if (busy)
				{
					AddressList.this.orginalTitel = getPartName();
					setPartName("I'm doing a job right now...");
				}
				else
				{
					setPartName(AddressList.this.orginalTitel);
				}

				return Status.OK_STATUS;
			}
		};
		uiJob.schedule();
	}
}