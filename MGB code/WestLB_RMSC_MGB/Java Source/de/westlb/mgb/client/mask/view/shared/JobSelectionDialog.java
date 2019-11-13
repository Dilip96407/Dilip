package de.westlb.mgb.client.mask.view.shared;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.westlb.mgb.client.mask.model.shared.JobSelectionModel;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.ui.util.ClientUtils;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.DualListBox;
import de.westlb_systems.xaf.ui.view.EditDialog;

/**
 * @author M. Boerner
 *
 */
public class JobSelectionDialog extends AbstractView implements EditDialog {

	/**
     * 
     */
    private static final long serialVersionUID = 778974348526096686L;
    private DualListBox dualListBox = null; 
	private JobSelectionModel jobSelectionModel = null;
	private String RESOURCE_BASE = getResourceBase();
	private Dimension minSize = new Dimension(390, 160);	
	
	private static class Item {
		JobVo jobVo;
		String jobName;
		Long jobId;
		
		public Item(JobVo jobVo) {
			this.jobVo = jobVo;
			this.jobId = jobVo.getJobId();
			this.jobName = ClientUtils.getNameForJob(jobVo);
		}
		
		@Override
        public String toString() {
			return jobName;
		}
		
        /**
         * Returns the jobVo.
         * @return JobVo
         */
        @SuppressWarnings("unused")
        public JobVo getJobVo() {
            return jobVo;
        }
        
        /**
         * @see java.lang.Object#equals(Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            
        	if (!(obj instanceof Item)) {
        		return false;
        	}
        	
			return jobId.equals(((Item)obj).getJobId());
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
        	return jobId.hashCode();
        }

        /**
         * @return
         */
        public Long getJobId() {
            return jobId;
        }

	}
	
	/**
	 * Constructor for TeamSelectionView.
	 */
	public JobSelectionDialog(Model model) {
		super();
		init();
		initComponents();
		initLabels();
		setModel(model);
	}

	private void init() {
		dualListBox = new DualListBox();
	}

	private void initLabels() {
		dualListBox.setLeftTitle	(getResourceString(RESOURCE_BASE + "leftTitle"));
		dualListBox.setRightTitle	(getResourceString(RESOURCE_BASE + "rightTitle"));
		setTitle(getResourceString	(RESOURCE_BASE + "title"));
	}	

	private void initComponents() {
 		setMinimumSize(minSize);
		this.setLayout(new BorderLayout());
		this.add(dualListBox);		
	}
		
	private void fillView() {
		if (jobSelectionModel == null) {
			return;
		}
		
		List<Item> rightJobs = itemListFor(jobSelectionModel.getSelectedJobs());
		List<Item> leftJobs = itemListFor(jobSelectionModel.getAvailableJobs());
		List<Item> allJobs = itemListFor(jobSelectionModel.getAvailableJobs());

		Iterator<Item> iterator = rightJobs.iterator();
		while (iterator.hasNext()) {
			Object selectedJob = iterator.next();
			leftJobs.remove(selectedJob);
        }
		
		dualListBox.setData(allJobs.toArray(), leftJobs.toArray(), rightJobs.toArray());
	}

	private List<Item> itemListFor(JobVo[] jobs) {
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i=0; i < jobs.length; i++) {
			items.add(new Item(jobs[i]));
		}
		
		return items;
	}
	
	@SuppressWarnings("unused")
    private Item[] jobList2ItemArray(List<JobVo> jobs) {
		Item[] items = new Item[jobs.size()];		
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(jobs.get(i));
		}		
		
		return items;
	}
		
	@Override
    public boolean cancelData() {
		return true;
	}
	
	/**
	 * Setzen des View-Models
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof JobSelectionModel)) {
			throw new IllegalArgumentException("Model not instance of JobSelectionModel!");
		}
		super.setModel(newModel);
		jobSelectionModel = (JobSelectionModel)newModel;
		fillView();
	}


	@Override
    public boolean saveData() {
		if (jobSelectionModel == null) {
			return false;
		}
		
		int[] selectedRows = dualListBox.getSelectedRows();
		if (selectedRows.length >0 && selectedRows[0] >= 0) {
		    JobVo[] jobs = new JobVo[selectedRows.length];
		    for (int i = 0; i < selectedRows.length; i++) {
		        jobs[i] = jobSelectionModel.getAvailableJobs()[selectedRows[i]];
		    }	
		    jobSelectionModel.setSelectedJobs(jobs);
		    super.saveData();
		}
			
		return true;
	}
	
}
