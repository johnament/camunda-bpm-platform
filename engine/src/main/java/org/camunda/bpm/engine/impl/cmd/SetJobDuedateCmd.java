/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.engine.impl.cmd;

import java.io.Serializable;
import java.util.Date;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.cfg.CommandChecker;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;


/**
 * @author Kristin Polenz
 */
public class SetJobDuedateCmd implements Command<Void>, Serializable {

  private static final long serialVersionUID = 1L;

  private final String jobId;
  private final Date newDuedate;

  public SetJobDuedateCmd(String jobId, Date newDuedate) {
    if (jobId == null || jobId.length() < 1) {
      throw new ProcessEngineException("The job id is mandatory, but '" + jobId + "' has been provided.");
    }
    this.jobId = jobId;
    this.newDuedate = newDuedate;
  }

  public Void execute(CommandContext commandContext) {
    JobEntity job = commandContext
            .getJobManager()
            .findJobById(jobId);
    if (job != null) {

      for(CommandChecker checker : commandContext.getProcessEngineConfiguration().getCommandCheckers()) {
        checker.checkUpdateJob(job);
      }

      job.setDuedate(newDuedate);
    } else {
      throw new ProcessEngineException("No job found with id '" + jobId + "'.");
    }
    return null;
  }
}
