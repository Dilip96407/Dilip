package de.westlb.mgb.server.service.archive;

import junit.framework.TestCase;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;

public class ArchiveServiceTest extends TestCase {
	public void testArchive() throws Exception {
		Session session = StoreSingleton.getUniqueInstance().openSession();
		@SuppressWarnings("unused")
        JobImpl job = (JobImpl)session.select(JobImpl.class, Long.valueOf(586426));
//        new ArchiveService().archiveJobs(session, 365, 5, "c:/Data/tmp", false);
        new ArchiveService().archiveJobs(365, 5, "c:/Data/tmp", false);
		session.close();
	}
}
