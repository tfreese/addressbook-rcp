package de.freese.addressbook.perspectives;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IViewLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.freese.addressbook.views.AddressList;

/**
 * @author Thomas Freese
 */
public class AddressesPerspective implements IPerspectiveFactory
{
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "de.freese.addressbook.perspective.Addresses";

	/**
	 * Erstellt ein neues {@link AddressesPerspective} Object.
	 */
	public AddressesPerspective()
	{
		super();
	}

	/**
	 * Add fast views to the perspective.
	 * 
	 * @param layout {@link IPageLayout}
	 */
	private void addFastViews(final IPageLayout layout)
	{
		// Empty
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 * 
	 * @param layout {@link IPageLayout}
	 */
	private void addPerspectiveShortcuts(final IPageLayout layout)
	{
		// Empty
	}

	/**
	 * Add view shortcuts to the perspective.
	 * 
	 * @param layout {@link IPageLayout}
	 */
	private void addViewShortcuts(final IPageLayout layout)
	{
		// Empty
	}

	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(final IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);

		IViewLayout listViewLayout = layout.getViewLayout(AddressList.ID);
		listViewLayout.setCloseable(false);

		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);

		IFolderLayout folderLayout =
				layout.createFolder("folder", IPageLayout.BOTTOM, 0.5f, IPageLayout.ID_EDITOR_AREA);
		folderLayout.addView("org.eclipse.ui.internal.console.ConsoleView");
		folderLayout.addView("org.eclipse.ui.views.ProgressView");

		layout.addView("de.freese.addressbook.views.AddressList", IPageLayout.TOP, 0.5f, "folder");
		layout.addView("de.freese.addressbook.views.AddressView", IPageLayout.RIGHT, 0.5f,
				"de.freese.addressbook.views.AddressList");

		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.addPerspectiveListener(new IPerspectiveListener()
				{

					/**
					 * @see org.eclipse.ui.IPerspectiveListener#perspectiveActivated(org.eclipse.ui.IWorkbenchPage,
					 *      org.eclipse.ui.IPerspectiveDescriptor)
					 */
					@Override
					public void perspectiveActivated(final IWorkbenchPage page,
														final IPerspectiveDescriptor perspective)
					{
						// Empty
					}

					/**
					 * @see org.eclipse.ui.IPerspectiveListener#perspectiveChanged(org.eclipse.ui.IWorkbenchPage,
					 *      org.eclipse.ui.IPerspectiveDescriptor, java.lang.String)
					 */
					@Override
					public void perspectiveChanged(final IWorkbenchPage page,
													final IPerspectiveDescriptor perspective,
													final String changeId)
					{
						if (page.isEditorAreaVisible())
						{
							IEditorReference[] editores = page.getEditorReferences();

							if (editores.length <= 0)
							{
								page.setEditorAreaVisible(false);
							}
						}
					}
				});
	}
}
