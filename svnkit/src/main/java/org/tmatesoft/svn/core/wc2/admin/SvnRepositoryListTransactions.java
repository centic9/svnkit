package org.tmatesoft.svn.core.wc2.admin;

import java.io.File;
import org.tmatesoft.svn.core.wc.admin.SVNAdminEvent;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnReceivingOperation;

public class SvnRepositoryListTransactions extends SvnReceivingOperation<SVNAdminEvent> {
    
    private File repositoryRoot;
    
    public SvnRepositoryListTransactions(SvnOperationFactory factory) {
        super(factory);
    }

	public File getRepositoryRoot() {
		return repositoryRoot;
	}

	public void setRepositoryRoot(File repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}

	    
}