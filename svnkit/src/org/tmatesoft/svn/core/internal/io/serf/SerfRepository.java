/*
 * ====================================================================
 * Copyright (c) 2004-2008 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html.
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */
package org.tmatesoft.svn.core.internal.io.serf;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.tmatesoft.svn.core.ISVNDirEntryHandler;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLock;
import org.tmatesoft.svn.core.SVNMergeInfoInheritance;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVConnection;
import org.tmatesoft.svn.core.internal.io.dav.DAVProperties;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepository;
import org.tmatesoft.svn.core.internal.io.dav.handlers.DAVEditorHandler;
import org.tmatesoft.svn.core.internal.io.dav.http.IHTTPConnectionFactory;
import org.tmatesoft.svn.core.internal.util.SVNHashMap;
import org.tmatesoft.svn.core.internal.wc.SVNDepthFilterEditor;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.ISVNFileRevisionHandler;
import org.tmatesoft.svn.core.io.ISVNLocationEntryHandler;
import org.tmatesoft.svn.core.io.ISVNLocationSegmentHandler;
import org.tmatesoft.svn.core.io.ISVNLockHandler;
import org.tmatesoft.svn.core.io.ISVNReplayHandler;
import org.tmatesoft.svn.core.io.ISVNReporterBaton;
import org.tmatesoft.svn.core.io.ISVNSession;
import org.tmatesoft.svn.core.io.ISVNWorkspaceMediator;
import org.tmatesoft.svn.core.io.SVNCapability;
import org.tmatesoft.svn.core.io.SVNRepository;


/**
 * @version 1.2.0
 * @author  TMate Software Ltd.
 */
public class SerfRepository extends DAVRepository {

    private DAVProperties myResourcePropertiesCache;
    private String myVCCPath;
    
    protected SerfRepository(IHTTPConnectionFactory connectionFactory, SVNURL location, ISVNSession options) {
        super(connectionFactory, location, options);
    }
    
    public void update(long revision, String target, SVNDepth depth, boolean sendCopyFromArgs, 
            ISVNReporterBaton reporter, ISVNEditor editor) throws SVNException {
//        runReport(getLocation(), revision, target, null, depth, false, false, true, sendCopyFromArgs, true, 
//                false, false, reporter, editor);
    }


    /*
    private void runReport(SVNURL url, long targetRevision, String target, String dstPath, SVNDepth depth, 
            boolean ignoreAncestry, boolean resourceWalk, boolean fetchContents, boolean sendCopyFromArgs, 
            boolean sendAll, boolean closeEditorOnException, boolean spool, ISVNReporterBaton reporter, 
            ISVNEditor editor) throws SVNException {
        boolean serverSupportsDepth = hasCapability(SVNCapability.DEPTH);
        if (depth != SVNDepth.FILES && depth != SVNDepth.INFINITY && !serverSupportsDepth) {
            editor = SVNDepthFilterEditor.getDepthFilterEditor(depth, editor, target != null);
        }
        
        SerfEditorHandler handler = null;
        try {
            openConnection();
            Map lockTokens = new SVNHashMap();
            StringBuffer request = DAVEditorHandler.generateEditorRequest(myConnection, null, 
                    url.toString(), targetRevision, target, dstPath, depth, lockTokens, ignoreAncestry, 
                    resourceWalk, fetchContents, sendCopyFromArgs, sendAll, reporter);
            handler = new DAVEditorHandler(myConnectionFactory, this, editor, lockTokens, fetchContents, 
                    target != null && !"".equals(target));
            String bcPath = SVNEncodingUtil.uriEncode(getLocation().getPath());
            try {
                bcPath = DAVUtil.getVCCPath(myConnection, this, bcPath);
            } catch (SVNException e) {
                if (closeEditorOnException) {
                    editor.closeEdit();
                }
                throw e;
            }
            HTTPStatus status = myConnection.doReport(bcPath, request, handler, spool);
            if (status.getError() != null) {
                SVNErrorManager.error(status.getError(), SVNLogType.NETWORK);
            }
        } finally {
            if (handler != null) {
                handler.closeConnection();
            }
            closeConnection();
        }
        
    }

    public String discoverRoot(String path) throws SVNException {
        if (getVCCPath() != null && path == null) {
            return getVCCPath();
        }
        
        
    }
*/
    protected String getVCCPath() {
        return myVCCPath;
    }
    
    protected DAVProperties getResourceProperties() {
        return myResourcePropertiesCache;
    }
    
    protected void setResourceProperties(DAVProperties props) {
        myResourcePropertiesCache = props;
    }
}
