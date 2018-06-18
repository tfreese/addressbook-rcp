/**
 * Created: 24.07.2012
 */

package de.freese.addressbook.perspectives;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Thomas Freese
 */
public class EditorAreaWatcher implements IPartListener
{
	/**
	 * 
	 */
	private final IWorkbenchPage page;

	/**
	 * Erstellt ein neues {@link EditorAreaWatcher} Object.
	 * 
	 * @param page {@link IWorkbenchPage}
	 */
	public EditorAreaWatcher(final IWorkbenchPage page)
	{
		super();

		this.page = page;

		this.page.addPartListener(this);
	}

	/**
	 * 
	 */
	public void dispose()
	{
		this.page.removePartListener(this);
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partActivated(final IWorkbenchPart part)
	{
		// Empty

	}

	/**
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(final IWorkbenchPart part)
	{
		// Empty
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partClosed(final IWorkbenchPart part)
	{
		if (this.page.isEditorAreaVisible())
		{
			IEditorReference[] editores = this.page.getEditorReferences();

			if (editores.length <= 0)
			{
				this.page.setEditorAreaVisible(false);
			}
		}
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partDeactivated(final IWorkbenchPart part)
	{
		// Empty
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partOpened(final IWorkbenchPart part)
	{
		// Empty
	}
}
