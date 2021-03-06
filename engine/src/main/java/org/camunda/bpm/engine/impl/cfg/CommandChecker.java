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

package org.camunda.bpm.engine.impl.cfg;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.impl.batch.BatchEntity;
import org.camunda.bpm.engine.impl.batch.history.HistoricBatchEntity;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionDefinitionEntity;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionRequirementsDefinitionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricJobLogEventEntity;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.CaseExecution;

/**
 * Is invoked while executing a command to check if the current operation is
 * allowed on the entity. If it is not allowed, the checker throws a
 * {@link ProcessEngineException}.
 */
public interface CommandChecker {

  /**
   * Checks if it is allowed to evaluate the given decision.
   */
  void checkEvaluateDecision(DecisionDefinition decisionDefinition);

  /**
   * Checks if it is allowed to create an instance of the given process definition.
   */
  void checkCreateProcessInstance(ProcessDefinition processDefinition);

  /**
   * Checks if it is allowed to read the given process definition.
   */
  void checkReadProcessDefinition(ProcessDefinition processDefinition);

  /**
   * Checks if it is allowed to create an instance of the given case definition.
   */
  void checkCreateCaseInstance(CaseDefinition caseDefinition);

  /**
   * Checks if it is allowed to update a process definition of the given process definition id.
   */
  void checkUpdateProcessDefinitionById(String processDefinitionId);

  /**
   * Checks if it is allowed to update a process instance of the given process definition id.
   */
  void checkUpdateProcessInstanceByProcessDefinitionId(String processDefinitionId);

  /**
   * Checks if it is allowed to update a process definition of the given process definition key.
   */
  void checkUpdateProcessDefinitionByKey(String processDefinitionKey);

  /**
   *  Checks if it is allowed to update a process instance of the given process definition key.
   */
  void checkUpdateProcessInstanceByProcessDefinitionKey(String processDefinitionKey);

  /**
   * Checks if it is allowed to update a process instance of the given process instance id.
   */
  void checkUpdateProcessInstanceById(String processInstanceId);

  /**
   * Checks if it is allowed to update a process instance of the given execution.
   */
  void checkUpdateProcessInstance(ExecutionEntity execution);

  void checkCreateMigrationPlan(ProcessDefinition sourceProcessDefinition, ProcessDefinition targetProcessDefinition);

  void checkMigrateProcessInstance(ExecutionEntity processInstance, ProcessDefinition targetProcessDefinition);

  void checkReadProcessInstance(String processInstanceId);

  /**
   * Checks if it is allowed to read the given job. 
   */
  void checkReadJob(JobEntity job);

  /**
   * Checks if it is allowed to update the given job.
   */
  void checkUpdateJob(JobEntity job);

  /**
   * Checks if it is allowed to read a process instance of the given execution.
   */
  void checkReadProcessInstance(ExecutionEntity execution);

  /**
   * Check if it is allowed to delete a process instance of the given execution.
   */
  void checkDeleteProcessInstance(ExecutionEntity execution);

  /**
   * Check if it is allowed to read a task.
   */
  void checkReadTask(TaskEntity task);

  /**
   * Check if it is allowed to update a task
   */
  void checkUpdateTask(TaskEntity task);

  /**
   * Check if it is allowed to delete a batch
   */
  void checkDeleteBatch(BatchEntity batch);

  /**
   * Check if it is allowed to delete a historic batch
   */
  void checkDeleteHistoricBatch(HistoricBatchEntity batch);

  /**
   * Check if it is allowed to suspend a batch
   */
  void checkSuspendBatch(BatchEntity batch);

  /**
   * Check if it is allowed to activate a batch
   */
  void checkActivateBatch(BatchEntity batch);

  /**
   * Checks if it is allowed to create a deployment.
   */
  void checkCreateDeployment();

  /**
   * Checks if it is allowed to read a deployment of the given deployment id.
   */
  void checkReadDeployment(String deploymentId);

  /**
   * Checks if it is allowed to delete a deployment of the given deployment id.
   */
  void checkDeleteDeployment(String deploymentId);

  /**
   * Check if it is allowed to assign a task
   */
  void checkTaskAssign(TaskEntity task);

  /**
   * Check if it is allowed to create a task
   */
  void checkCreateTask(TaskEntity task);

  /**
   * Check if it is allowed to create a task
   */
  void checkCreateTask();

  /**
   * Check if it is allowed to work on a task
   */
  void checkTaskWork(TaskEntity task);
  
  /**
   *  Check if it is allowed to delete a task
   */
  void checkDeleteTask(TaskEntity task);

  /**
   * Checks if it is allowed to read the given decision definition.
   */
  void checkReadDecisionDefinition(DecisionDefinitionEntity decisionDefinition);

  /**
   * Checks if it is allowed to read the given decision requirements definition.
   */
  void checkReadDecisionRequirementsDefinition(DecisionRequirementsDefinitionEntity decisionRequirementsDefinition);

  /**
   * Checks if it is allowed to read the given case definition.
   */
  void checkReadCaseDefinition(CaseDefinition caseDefinition);

  /**
   * Checks if it is allowed to delete the given historic task instance.
   */
  void checkDeleteHistoricTaskInstance(HistoricTaskInstanceEntity task);

  /**
   * Checks if it is allowed to delete the given historic process instance.
   */
  void checkDeleteHistoricProcessInstance(HistoricProcessInstance instance);

  /**
   * Checks if it is allowed to delete the given historic case instance.
   */
  void checkDeleteHistoricCaseInstance(HistoricCaseInstance instance);

  /**
   * Checks if it is allowed to delete the historic decision instance of the given
   * decision definition key.
   */
  void checkDeleteHistoricDecisionInstance(String decisionDefinitionKey);

  /**
   * Checks if it is allowed to read the given historic job log.
   */
  void checkReadHistoricJobLog(HistoricJobLogEventEntity historicJobLog);

  /**
   * Check if it is allowed to read the history for any process definition.
   */
  void checkReadHistoryAnyProcessDefinition();

  /**
   * Check if it is allowed to update a case instance of the given case execution.
   */
  void checkUpdateCaseInstance(CaseExecution caseExecution);

  /**
   * Check if it is allowed to delete the user operation log of the given user operation log entry.
   */
  void checkDeleteUserOperationLog(UserOperationLogEntry entry);

  /**
   * Check if it is allowed to read a case instance of the given case execution.
   */
  void checkReadCaseInstance(CaseExecution caseExecution);

}
