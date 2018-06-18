/**
 * Created: 21.06.2012
 */

package de.freese.addressbook.views;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.freese.addressbook.entities.Address;
import de.freese.addressbook.entities.Country;
import de.freese.addressbook.services.AddressBookServices;
import de.freese.addressbook.services.IAddressService;

/**
 * @author Thomas Freese
 */
public class AddressView extends ViewPart implements ISelectionListener
{
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "de.freese.addressbook.views.AddressView"; //$NON-NLS-1$

	/**
	 * 
	 */
	private Combo comboLand;

	/**
	 * 
	 */
	private ComboViewer comboViewer = null;

	/**
	 * 
	 */
	private WritableValue currentAddress = new WritableValue();

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;

	/**
	 * 
	 */
	private Text textName = null;

	/**
	 * 
	 */
	private Text textOrt = null;

	/**
	 * 
	 */
	private Text textPLZ = null;

	/**
	 * 
	 */
	private Text textStrasse = null;

	/**
	 * Erstellt ein neues {@link AddressView} Object.
	 */
	public AddressView()
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
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		parent.setLayout(new GridLayout(3, false));

		Label lblName = new Label(parent, SWT.NONE);
		lblName.setText(Messages.AddressView_name);

		this.textName = new Text(parent, SWT.BORDER);
		this.textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		ControlDecoration decoration = new ControlDecoration(this.textName, SWT.LEFT | SWT.BOTTOM);
		Image errorImage =
				FieldDecorationRegistry.getDefault()
						.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();
		decoration.setImage(errorImage);
		decoration.setDescriptionText("Name der Addresse");
		decoration.setShowHover(true);

		Label lblStrasse = new Label(parent, SWT.NONE);
		lblStrasse.setText(Messages.AddressView_strasse);

		this.textStrasse = new Text(parent, SWT.BORDER);
		this.textStrasse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label lblPlzOrt = new Label(parent, SWT.NONE);
		lblPlzOrt.setText(Messages.AddressView_plzort);

		this.textPLZ = new Text(parent, SWT.BORDER);
		this.textPLZ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		this.textOrt = new Text(parent, SWT.BORDER);
		this.textOrt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLand = new Label(parent, SWT.NONE);
		lblLand.setText(Messages.AddressView_land);

		this.comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		this.comboLand = this.comboViewer.getCombo();
		this.comboLand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		this.comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		this.comboViewer.setLabelProvider(new LabelProvider()
		{
			/**
			 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(final Object element)
			{
				Country country = (Country) element;

				return country.getName();
			}
		});
		this.comboViewer.addPostSelectionChangedListener(new ISelectionChangedListener()
		{
			/**
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			@Override
			public void selectionChanged(final SelectionChangedEvent event)
			{
				if (event.getSelection().isEmpty())
				{
					return;
				}

				System.out.println("AddressView.ComboViewer.selectionChanged"); //$NON-NLS-1$
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Country selectedCountry = (Country) selection.getFirstElement();

				System.out.printf("%s selected\n", selectedCountry.getName()); //$NON-NLS-1$
			}
		});
		this.comboViewer.setComparator(new ViewerComparator());

		IAddressService service = AddressBookServices.getAddressService();
		List<Country> countries = service.getAllCountries();

		// Kann auch über IObservableValue uiCountry.setValue gesetzt werden.
		this.comboViewer.setInput(countries);
		this.comboLand.select(0);
		// this.comboViewer.setSelection(new StructuredSelection(countries.get(0)));

		createActions();
		initializeToolBar();
		initializeMenu();
		initializeSelection();
		this.m_bindingContext = initDataBindings();
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose()
	{
		ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.removePostSelectionListener(this);

		super.dispose();
	}

	// TODO Binding: ViewersObservables->ViewerProperties->WidgetProperties
	// ControlDecorationSupport.create(binding, SWT.TOP | SWT.RIGHT);

	/**
	 * @return {@link DataBindingContext}
	 */
	protected DataBindingContext initDataBindings()
	{
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextNameObserveWidget =
				WidgetProperties.text(SWT.Modify).observe(this.textName);
		IObservableValue currentAddressNameObserveDetailValue =
				PojoProperties.value(Address.class, "name", String.class).observeDetail(
						this.currentAddress);
		bindingContext.bindValue(observeTextTextNameObserveWidget,
				currentAddressNameObserveDetailValue, null, null);

		//
		IObservableValue observeTextTextStrasseObserveWidget =
				WidgetProperties.text(SWT.Modify).observe(this.textStrasse);
		IObservableValue currentAddressStreetObserveDetailValue =
				PojoProperties.value(Address.class, "street", String.class).observeDetail(
						this.currentAddress);
		bindingContext.bindValue(observeTextTextStrasseObserveWidget,
				currentAddressStreetObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextPLZObserveWidget =
				WidgetProperties.text(SWT.Modify).observe(this.textPLZ);
		IObservableValue currentAddressZipObserveDetailValue =
				PojoProperties.value(Address.class, "zip", String.class).observeDetail(
						this.currentAddress);
		bindingContext.bindValue(observeTextTextPLZObserveWidget,
				currentAddressZipObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextOrtObserveWidget =
				WidgetProperties.text(SWT.Modify).observe(this.textOrt);
		IObservableValue currentAddressCityObserveDetailValue =
				PojoProperties.value(Address.class, "city", String.class).observeDetail(
						this.currentAddress);
		bindingContext.bindValue(observeTextTextOrtObserveWidget,
				currentAddressCityObserveDetailValue, null, null);

		// ViewerProperties
		IObservableValue uiCountry = ViewersObservables.observeSingleSelection(this.comboViewer);
		IObservableValue modelCountry =
				PojoObservables.observeDetailValue(this.currentAddress, "country", Country.class);
		bindingContext.bindValue(uiCountry, modelCountry);
		//
		return bindingContext;
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu()
	{
		// IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	/**
	 * 
	 */
	private void initializeSelection()
	{
		ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addPostSelectionListener(this);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar()
	{
		// IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
		if (selection.isEmpty())
		{
			setAddress(null);
			return;
		}

		if (!(selection instanceof IStructuredSelection))
		{
			return;
		}

		System.out.println("AddressView.selectionChanged"); //$NON-NLS-1$
		// System.err.println("sddd");
		Iterator<Object> iterator = ((IStructuredSelection) selection).iterator();

		while (iterator.hasNext())
		{
			Object object = iterator.next();

			if (object instanceof Address)
			{
				setAddress((Address) object);
			}
		}
	}

	/**
	 * @param address {@link Address}
	 */
	private void setAddress(final Address address)
	{
		this.currentAddress.setValue(address);
		//		this.textName.setText(""); //$NON-NLS-1$
		//		this.textStrasse.setText(""); //$NON-NLS-1$
		//		this.textPLZ.setText(""); //$NON-NLS-1$
		//		this.textOrt.setText(""); //$NON-NLS-1$
		// this.comboViewer.setSelection(null);
		//
		// if (address != null)
		// {
		// this.textName.setText(address.getName());
		// this.textStrasse.setText(address.getStreet());
		// this.textPLZ.setText(address.getZip());
		// this.textOrt.setText(address.getCity());
		//
		// this.comboViewer.setSelection(new StructuredSelection(address.getCountry()));
		// }
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{
		this.textName.setFocus();
	}
}
