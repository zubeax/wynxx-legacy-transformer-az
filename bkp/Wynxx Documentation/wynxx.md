--- INFO DO DOCUMENTO ---
PROJETO: Wynxx Knowledge Base
FONTE ORIGINAL: https://docs.wynxx.app/introduction
DATA DE GERAÇÃO: 27/02/2026 às 16:40:21
--------------------------



# ORIGEM: https://docs.wynxx.app/introduction
Getting Started

# What is Wynxx?

Wynxx is a comprehensive AI development platform that accelerates software development through intelligent automation. The platform provides a suite of AI-powered tools accessible via web portal and VS Code extension.

---

## Platform Features

### Code Generation & Testing

| Feature | Description | Access |
| --- | --- | --- |
| 🧪 **[Code Tester](/guides/features/code-tester)** | Generate unit and integration tests automatically from source code | Portal, VS Code |
| 🎭 **[Functional Tester](/guides/features/functional-tester)** | Drive Playwright end-to-end tests using natural language prompts | Playwright Integration |

### Code Quality & Documentation

| Feature | Description | Access |
| --- | --- | --- |
| 📝 **[Code Documenter](/guides/features/code-documenter)** | Transform source code into clear, structured documentation | Portal, VS Code |
| 🔧 **[Code Fixer](/guides/features/code-fixer)** | Automatically fix code issues detected by SAST tools (SonarQube, etc.) | Portal |
| 🗞️ **[Code Reviewer](/api-guides/code-reviewer)** | Automated code review and feedback for pull requests | API |

### AI Assistants

| Feature | Description | Access |
| --- | --- | --- |
| 💬 **[Code Dialoguer](/guides/features/code-dialoguer)** | Interactive chat for code questions, explanations, and refactoring | Portal, VS Code |
| 🏗️ **[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)** | Generate architecture diagrams (UML, Sequence, Class) from code | VS Code |

### Product & Requirements

| Feature | Description | Access |
| --- | --- | --- |
| 🧠 **[Story Creator 2.0](/guides/features/story-creator2)** | Transform use cases into structured backlogs with epics, features, and user stories | Portal |
| 📋 **[Story Creator 1.0](/guides/features/story-creator)** | Legacy story creation interface | Portal |

### Legacy Modernization

| Feature | Description | Access |
| --- | --- | --- |
| 🔄 **[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)** | Extract business rules from legacy code (COBOL, JCL) and convert to modern languages or user stories | VS Code |

---

## Access Methods

### Web Portal

The main Wynxx web application provides access to:

* Code Documenter, Code Tester, Code Fixer
* Code Dialoguer, Story Creator
* Settings, User Management, Cost Control

### VS Code Extension (Wynxx Assist)

The [Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist) brings AI capabilities directly into your IDE:

* **Architect AI** - Generate architecture diagrams
* **Code Documenter** - Document code in-editor
* **Code Tester** - Generate tests without leaving VS Code
* **Code Dialoguer** - AI chat integrated in your workflow
* **Legacy Transformer** - Modernize legacy code

### API

All features are accessible via REST API for CI/CD integration and automation. See the [API Reference](/api) for details.

---

## Administration Features

| Feature | Description |
| --- | --- |
| 👥 **[User Control](/settings/user-control)** | Manage users, roles, and permissions |
| 👪 **[Group Control](/settings/group-control)** | Organize users into groups with shared settings |
| 💰 **[Cost Control](/settings/cost-control)** | Monitor LLM usage, set budgets, and track spending |
| ⚙️ **[Application Settings](/settings/application)** | Configure LLMs, integrations, and system settings |
| 🧙 **[Setup Wizard](/settings/wizard)** | Guided initial configuration |

---

## Integrations

### LLM Providers

| Provider | Chat Models | Embedding Models |
| --- | --- | --- |
| **Azure OpenAI** | GPT-5, GPT-4.1, GPT-4o | text-embedding-3-large |
| **AWS Bedrock** | Claude Opus 4.5, Claude Sonnet 4.5, Claude Sonnet 3.7 | amazon.titan-embed-text-v1 |
| **Google Gemini** | Gemini 2.5 Pro | text-embedding-004 |
| **LiteLLM** | Multi-provider gateway | - |

### Version Control Systems

| Platform | Supported Version |
| --- | --- |
| **GitHub** | GitHub Cloud |
| **GitLab** | API v4 (Cloud & Self-hosted) |
| **Azure DevOps** | Cloud |

### SAST Tools

| Tool | Supported Version |
| --- | --- |
| **SonarQube** | 2025.x |
| **SonarCloud** | 2025.x |

### Agile Tools

| Tool | Supported Version |
| --- | --- |
| **Jira** | Jira Cloud & Jira Server (API v3) |
| **Azure Boards** | Cloud |

---

## Quick Links

* [Prerequisites](/installation/prerequisites) - Requirements checklist
* [Installation](/installation) - Deploy Wynxx on your infrastructure
* [FAQ](/getting-started/faq) - Common questions and troubleshooting
* [API Reference](/api) - REST API documentation

Last modified on February 26, 2026

[FAQ (Frequently Asked Questions)](/getting-started/faq)

On this page

* [Platform Features](/introduction#platform-features)
  + [Code Generation & Testing](/introduction#code-generation--testing)
  + [Code Quality & Documentation](/introduction#code-quality--documentation)
  + [AI Assistants](/introduction#ai-assistants)
  + [Product & Requirements](/introduction#product--requirements)
  + [Legacy Modernization](/introduction#legacy-modernization)
* [Access Methods](/introduction#access-methods)
  + [Web Portal](/introduction#web-portal)
  + [VS Code Extension (Wynxx Assist)](/introduction#vs-code-extension-wynxx-assist)
  + [API](/introduction#api)
* [Administration Features](/introduction#administration-features)
* [Integrations](/introduction#integrations)
  + [LLM Providers](/introduction#llm-providers)
  + [Version Control Systems](/introduction#version-control-systems)
  + [SAST Tools](/introduction#sast-tools)
  + [Agile Tools](/introduction#agile-tools)
* [Quick Links](/introduction#quick-links)

---


# ORIGEM: https://docs.wynxx.app/settings/group-control
Settings

# Group Control

This page explains how to manage Groups in the Group Control area of Settings. Follow the steps below to add and manage groups.

## Access

* Go to User Control
* Access the Groups section to manage group organization

In this page you can view or edit groups:

![Group Control – Main Page](/assets/group-control-Cste4BLj.png)

## Add a Group

Use groups to organize permissions and membership for better user management.

Fields:

* Group Name: the display name of the group
* Roles: assign roles/permissions to the group

Steps:

1. Open the Groups section
2. Click Add Group
3. Fill in group name
4. Add roles to the group
5. Save to create the group

![Group Control – Create group](/assets/create-group-BXI1pFqa.png)

## Edit a Group

To modify existing groups, follow these steps:

1. Open the Groups section
2. Select the group to edit
3. Make the desired changes
4. Save the changes

## Tips

* Use clear, descriptive group names reflecting team or function
* Consider organizational structure when naming groups
* Review group permissions regularly for security
* Plan group hierarchy for scalable user management

## Troubleshooting

* Duplicate name: choose a unique Group Name
* Validation errors: confirm required fields and proper naming conventions
* Permission issues: ensure proper access rights for group creation
* Missing options: refresh the page or check user permissions

Learn how to create and assign users to groups:

* [User Control](/settings/user-control)

Last modified on February 26, 2026

[Cost Control](/settings/cost-control)[User Control](/settings/user-control)

On this page

* [Access](/settings/group-control#access)
* [Add a Group](/settings/group-control#add-a-group)
* [Edit a Group](/settings/group-control#edit-a-group)
* [Tips](/settings/group-control#tips)
* [Troubleshooting](/settings/group-control#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/api
Gft.Ai.Impact.Api.WebApi

# Agile

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/preview

### Request Body

* `LevelTypeId`string
* `ProjectId`string
* `RequestTitle`string
* `RequestDescription`string
* `ParentId`string
* `UseCaseId`string
* `WorkItemId`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/preview

```
curl --request POST \
  --url __API_BASE_URL__/bff/agile/preview \
  --header 'Content-Type: application/json' \
  --data '
{
  "LevelTypeId": "LevelTypeId",
  "ProjectId": "ProjectId",
  "RequestTitle": "RequestTitle",
  "RequestDescription": "RequestDescription",
  "ParentId": "ParentId",
  "UseCaseId": "UseCaseId",
  "WorkItemId": "WorkItemId",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "JobName": "JobName",
  "RunName": "RunName",
  "AdditionalInstructions": "AdditionalInstructions",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "Conventions": "Conventions",
  "SystemPrompt.Id": "SystemPrompt.Id",
  "SystemPrompt.Content": "SystemPrompt.Content",
  "SystemPrompt.JobType": "SystemPrompt.JobType",
  "SystemPrompt.Description": "SystemPrompt.Description",
  "InputFolder": "InputFolder",
  "OutputFolder": "OutputFolder",
  "SearchPattern": "SearchPattern",
  "TargetExtension": "TargetExtension",
  "LlmTools": [
    "string"
  ],
  "ResponseJsonFormat": "ResponseJsonFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "UserId": "UserId",
  "UserName": "UserName",
  "JobType": "JobType",
  "IsBudgetByUser": true,
  "LlmType": "LlmType",
  "EncodingType": "EncodingType",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "LevelTypeId": "LevelTypeId",
  "ProjectId": "ProjectId",
  "RequestTitle": "RequestTitle",
  "RequestDescription": "RequestDescription",
  "ParentId": "ParentId",
  "UseCaseId": "UseCaseId",
  "WorkItemId": "WorkItemId",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "JobName": "JobName",
  "RunName": "RunName",
  "AdditionalInstructions": "AdditionalInstructions",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "Conventions": "Conventions",
  "SystemPrompt.Id": "SystemPrompt.Id",
  "SystemPrompt.Content": "SystemPrompt.Content",
  "SystemPrompt.JobType": "SystemPrompt.JobType",
  "SystemPrompt.Description": "SystemPrompt.Description",
  "InputFolder": "InputFolder",
  "OutputFolder": "OutputFolder",
  "SearchPattern": "SearchPattern",
  "TargetExtension": "TargetExtension",
  "LlmTools": [
    "string"
  ],
  "ResponseJsonFormat": "ResponseJsonFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "UserId": "UserId",
  "UserName": "UserName",
  "JobType": "JobType",
  "IsBudgetByUser": true,
  "LlmType": "LlmType",
  "EncodingType": "EncodingType",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
string
```

plain

text/plainapplication/jsontext/json

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/re-create

### Request Body

* `LevelTypeId`string
* `ProjectId`string
* `RequestTitle`string
* `RequestDescription`string
* `ParentId`string
* `UseCaseId`string
* `WorkItemId`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/re-create

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/story-points

### Request Body

* `WorkItemID`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/story-points

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/list

### Responses

Success

* `level`string | null
* `mappingFieldName`string | null
* `levelAlias`string | null
* `defaultPromptId`string | null
* `fields`array | null
* `children`array | null · `children (circular)`
* `issueType`string | null

GET /bff/agile/process-definition/list

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/{JobId}

### path Parameters

* `JobId`string · required · style: simple

### Responses

Success

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

GET /bff/agile/process-definition/{JobId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/{level}/next

### path Parameters

* `level`string · required · style: simple

### Responses

Success

No data returned

GET /bff/agile/process-definition/{level}/next

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition-sync-levels-totalizer/{JobId}

### path Parameters

* `JobId`string · required · style: simple

### Responses

Success

No data returned

GET /bff/agile/process-definition-sync-levels-totalizer/{JobId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/user-case/list

### Responses

Success

* `projectId`string | null
* `levelTypeId`string | null
* `requestTitle`string | null
* `requestDescription`string | null
* `jobId`string | null
* `id`string | null
* `modificationAt`string · date-time

GET /bff/agile/user-case/list

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

string

DELETE /bff/agile/work-item/{workItemID}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}/detail

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

* `id`string | null
* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `parentId`string | null
* `useCaseId`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `integrated`boolean | null
* `jobId`string | null
* `externalId`string | null
* `fields`array | null
* `parent`array | null · `parent (circular)`

GET /bff/agile/work-item/{workItemID}/detail

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}/lock

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

boolean

PUT /bff/agile/work-item/{workItemID}/lock

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/create

### Request Body

* `Id`string
* `IdProjects`string
* `ProjectId`string
* `LevelTypeId`string
* `LevelAliasTypeId`string
* `LevelTypeIdNext`string[]
* `LevelTypeIdAliasNext`string[]
* `ParentId`string
* `UseCaseId`string
* `Order`integer · int32
* `Blocked`boolean
* `Integrated`boolean
* `JobId`string
* `ExternalId`string
* `Fields`object[]
* `Parent`object[]

### Responses

Success

* `id`string | null
* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `parentId`string | null
* `useCaseId`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `integrated`boolean | null
* `jobId`string | null
* `externalId`string | null
* `fields`array | null
* `parent`array | null · `parent (circular)`

POST /bff/agile/work-item/create

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/edit

### Request Body

* `Id`string
* `IdProjects`string
* `ProjectId`string
* `LevelTypeId`string
* `LevelAliasTypeId`string
* `LevelTypeIdNext`string[]
* `LevelTypeIdAliasNext`string[]
* `ParentId`string
* `UseCaseId`string
* `Order`integer · int32
* `Blocked`boolean
* `Integrated`boolean
* `JobId`string
* `ExternalId`string
* `Fields`object[]
* `Parent`object[]

### Responses

Success

string

PUT /bff/agile/work-item/edit

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{LevelType}/config

### path Parameters

* `LevelType`string · required · style: simple

### Responses

Success

* `alias`string | null
* `type`string | null
* `name`string | null
* `value`string | null
* `values`array | null

GET /bff/agile/work-item/{LevelType}/config

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/sync-workitems

### Request Body

* `ProjectId`string
* `UseCaseId`string
* `BackLogServiceName`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/sync-workitems

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/re-ordem

### Request Body

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

### Responses

Success

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

PUT /bff/agile/re-ordem

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/level/{levelId}

### path Parameters

* `levelId`string · required · style: simple

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/agile/level/{levelId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/project/list

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/agile/project/list

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/agile/tree-by-usecase/{useCaseId}

### path Parameters

* `useCaseId`string · required · style: simple

### Responses

Success

string

DELETE /bff/agile/tree-by-usecase/{useCaseId}

---

[Ai](/api/ai)

---


# ORIGEM: https://docs.wynxx.app/settings/application
Settings

# Application Settings

This page covers the day-to-day configuration of Wynxx after the first-time Wizard. Use it to update credentials, switch providers, and fine-tune defaults without rerunning the entire setup.

> Tip: If you haven’t completed the first-time setup yet, see [Wizard Settings](/settings/wizard).

> ⚠️ **Important:** After you change any configuration, is necessary restart the backend container in your cluster.

## LLM settings

Adjust the Large Language Model provider and defaults used by Wynxx.

What you can change:

* Provider/type (Azure OpenAI, OpenAI, Google Gemini, AWS Bedrock)
* API key/credential reference and endpoint/base URL
* Model or deployment name (for chat and, if applicable, embeddings)
* Optional parameters (temperature, max tokens, system prompt)

How to update:

1. Open Settings → LLM
2. Select the provider and fill the required fields for that provider
3. Optionally tune temperature, max tokens, and system prompt
4. Click Test connection and then Save

![LLM settings screen](/assets/llms-CK0_crHb.png)

Related guides:

* Integrations → Overview: [/integrations](/integrations)
* Azure OpenAI: [/integrations/llms/azure-openai](/integrations/llms/azure-openai)
* AWS Bedrock: [/integrations/llms/aws-bedrock](/integrations/llms/aws-bedrock)
* Google Gemini: [/integrations/llms/google-gemini](/integrations/llms/google-gemini)
* LiteLLM Proxy: [/integrations/llms/litellm](/integrations/llms/litellm)

## Version control (VCS)

Configure repository access so Wynxx can read code, create branches, commit changes, and open/update pull requests.

What you can change:

* Provider (GitHub, Azure DevOps, GitLab)
* Organization/Owner, Project (for Azure DevOps), and Repository
* Personal Access Token (PAT) or app credentials with the minimum necessary scopes
* Optional: base URL for self-hosted instances (e.g., GitHub Enterprise, GitLab self-managed)

How to update:

1. Open Settings → VCS
2. Choose your provider
3. Provide organization/owner/project and repository details
4. Paste a PAT with repository read/write per your workflow needs
5. Click Test connection (if available) and Save

![VCS settings: provider, repository and token](/assets/vcs-DgDk9W2n.png)

Related guides:

* GitHub token scopes: [/integrations/vcs/github](/integrations/vcs/github)
* Azure DevOps PATs (Agile + Git): [/integrations/vcs/azure-devops](/integrations/vcs/azure-devops)
* GitLab tokens: [/integrations/vcs/gitlab](/integrations/vcs/gitlab)

Security notes:

* Prefer fine-grained or least-privilege tokens; rotate regularly

## SAST integration

Connect a static analysis provider to enrich reviews with code quality and security insights.

Supported providers (commonly used): SonarCloud, SonarQube.

What you can change:

* Provider (SonarCloud or SonarQube)
* Organization (SonarCloud) or Server URL (SonarQube)
* Project key/identifier
* Authentication token

How to update:

1. Open Settings → SAST
2. Select provider
3. Fill Organization/Server URL and Project key
4. Provide an access token with the required permissions
5. Test and Save

![SAST settings (SonarCloud/SonarQube)](/assets/sast-CUmC6jh4.png)

Related guide: Sonar (tokens and project key): [/integrations/sast/sonar](/integrations/sast/sonar)

## Token rotation and connection testing

* Rotate tokens periodically (GitHub/Azure DevOps/GitLab/Sonar) and update Settings accordingly
* Use Test connection where available to verify access before saving
* Keep the minimum scopes necessary; avoid broad admin-level permissions

## Jobs

Use the Jobs view to monitor and troubleshoot background operations (for example, repository syncs, code operations, or analysis tasks). You can check statuses, inspect details, and retry when available.

![Jobs status and details](/assets/jobs-IoUwEEXA.png)

## Troubleshooting

* Permission denied or 401/403: recheck token scopes and target repository/project access
* Self-hosted endpoints: confirm base URL, SSL, and firewall rules
* LLM errors: confirm model/deployment names, API key validity, quotas, region

Last modified on February 26, 2026

[Overview](/settings)[Cost Control](/settings/cost-control)

On this page

* [LLM settings](/settings/application#llm-settings)
* [Version control (VCS)](/settings/application#version-control-vcs)
* [SAST integration](/settings/application#sast-integration)
* [Token rotation and connection testing](/settings/application#token-rotation-and-connection-testing)
* [Jobs](/settings/application#jobs)
* [Troubleshooting](/settings/application#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/guides/features/functional-tester
Getting Started

# What is Wynxx?

Wynxx is a comprehensive AI development platform that accelerates software development through intelligent automation. The platform provides a suite of AI-powered tools accessible via web portal and VS Code extension.

---

## Platform Features

### Code Generation & Testing

| Feature | Description | Access |
| --- | --- | --- |
| 🧪 **[Code Tester](/guides/features/code-tester)** | Generate unit and integration tests automatically from source code | Portal, VS Code |
| 🎭 **[Functional Tester](/guides/features/functional-tester)** | Drive Playwright end-to-end tests using natural language prompts | Playwright Integration |

### Code Quality & Documentation

| Feature | Description | Access |
| --- | --- | --- |
| 📝 **[Code Documenter](/guides/features/code-documenter)** | Transform source code into clear, structured documentation | Portal, VS Code |
| 🔧 **[Code Fixer](/guides/features/code-fixer)** | Automatically fix code issues detected by SAST tools (SonarQube, etc.) | Portal |
| 🗞️ **[Code Reviewer](/api-guides/code-reviewer)** | Automated code review and feedback for pull requests | API |

### AI Assistants

| Feature | Description | Access |
| --- | --- | --- |
| 💬 **[Code Dialoguer](/guides/features/code-dialoguer)** | Interactive chat for code questions, explanations, and refactoring | Portal, VS Code |
| 🏗️ **[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)** | Generate architecture diagrams (UML, Sequence, Class) from code | VS Code |

### Product & Requirements

| Feature | Description | Access |
| --- | --- | --- |
| 🧠 **[Story Creator 2.0](/guides/features/story-creator2)** | Transform use cases into structured backlogs with epics, features, and user stories | Portal |
| 📋 **[Story Creator 1.0](/guides/features/story-creator)** | Legacy story creation interface | Portal |

### Legacy Modernization

| Feature | Description | Access |
| --- | --- | --- |
| 🔄 **[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)** | Extract business rules from legacy code (COBOL, JCL) and convert to modern languages or user stories | VS Code |

---

## Access Methods

### Web Portal

The main Wynxx web application provides access to:

* Code Documenter, Code Tester, Code Fixer
* Code Dialoguer, Story Creator
* Settings, User Management, Cost Control

### VS Code Extension (Wynxx Assist)

The [Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist) brings AI capabilities directly into your IDE:

* **Architect AI** - Generate architecture diagrams
* **Code Documenter** - Document code in-editor
* **Code Tester** - Generate tests without leaving VS Code
* **Code Dialoguer** - AI chat integrated in your workflow
* **Legacy Transformer** - Modernize legacy code

### API

All features are accessible via REST API for CI/CD integration and automation. See the [API Reference](/api) for details.

---

## Administration Features

| Feature | Description |
| --- | --- |
| 👥 **[User Control](/settings/user-control)** | Manage users, roles, and permissions |
| 👪 **[Group Control](/settings/group-control)** | Organize users into groups with shared settings |
| 💰 **[Cost Control](/settings/cost-control)** | Monitor LLM usage, set budgets, and track spending |
| ⚙️ **[Application Settings](/settings/application)** | Configure LLMs, integrations, and system settings |
| 🧙 **[Setup Wizard](/settings/wizard)** | Guided initial configuration |

---

## Integrations

### LLM Providers

| Provider | Chat Models | Embedding Models |
| --- | --- | --- |
| **Azure OpenAI** | GPT-5, GPT-4.1, GPT-4o | text-embedding-3-large |
| **AWS Bedrock** | Claude Opus 4.5, Claude Sonnet 4.5, Claude Sonnet 3.7 | amazon.titan-embed-text-v1 |
| **Google Gemini** | Gemini 2.5 Pro | text-embedding-004 |
| **LiteLLM** | Multi-provider gateway | - |

### Version Control Systems

| Platform | Supported Version |
| --- | --- |
| **GitHub** | GitHub Cloud |
| **GitLab** | API v4 (Cloud & Self-hosted) |
| **Azure DevOps** | Cloud |

### SAST Tools

| Tool | Supported Version |
| --- | --- |
| **SonarQube** | 2025.x |
| **SonarCloud** | 2025.x |

### Agile Tools

| Tool | Supported Version |
| --- | --- |
| **Jira** | Jira Cloud & Jira Server (API v3) |
| **Azure Boards** | Cloud |

---

## Quick Links

* [Prerequisites](/installation/prerequisites) - Requirements checklist
* [Installation](/installation) - Deploy Wynxx on your infrastructure
* [FAQ](/getting-started/faq) - Common questions and troubleshooting
* [API Reference](/api) - REST API documentation

Last modified on February 26, 2026

[FAQ (Frequently Asked Questions)](/getting-started/faq)

On this page

* [Platform Features](/introduction#platform-features)
  + [Code Generation & Testing](/introduction#code-generation--testing)
  + [Code Quality & Documentation](/introduction#code-quality--documentation)
  + [AI Assistants](/introduction#ai-assistants)
  + [Product & Requirements](/introduction#product--requirements)
  + [Legacy Modernization](/introduction#legacy-modernization)
* [Access Methods](/introduction#access-methods)
  + [Web Portal](/introduction#web-portal)
  + [VS Code Extension (Wynxx Assist)](/introduction#vs-code-extension-wynxx-assist)
  + [API](/introduction#api)
* [Administration Features](/introduction#administration-features)
* [Integrations](/introduction#integrations)
  + [LLM Providers](/introduction#llm-providers)
  + [Version Control Systems](/introduction#version-control-systems)
  + [SAST Tools](/introduction#sast-tools)
  + [Agile Tools](/introduction#agile-tools)
* [Quick Links](/introduction#quick-links)

---


# ORIGEM: https://docs.wynxx.app/integrations/sast/sonar
Sast

# SonarCloud / SonarQube Integration

This page explains, step by step, how to create a token for SonarCloud or SonarQube and how to find your Project Key. The token must allow Wynxx to view scanned projects and list vulnerabilities found in each project.

The instructions assume minimal prior knowledge and cover both SonarCloud (hosted) and SonarQube (self-managed).

## What you will create

You need one token that allows Wynxx to:

* View your projects
* Read issues (including vulnerabilities) for each project

Permissions model note:

* Tokens in SonarCloud/SonarQube inherit the permissions of the user or project they’re issued for. There are no granular “scopes” to select like in some other systems. Make sure the account or project role associated with the token has at least “Browse” access to the projects you want Wynxx to read.

## SonarCloud: Create a token (recommended approach)

1. Go to [https://sonarcloud.io](https://sonarcloud.io) and sign in (use your GitHub/Azure DevOps/GitLab/Bitbucket account as applicable).
2. Open your avatar (top-right) → My Account → Security.
3. Under Tokens, enter a Token name (for example: Wynxx Read Token).
4. Optional: Set an expiration (shorter is safer) and plan to rotate regularly.
5. Click Generate and copy the token. You won’t be able to see it again.

Required permissions: Ensure the user account that generated the token is a member of the target organization and has at least the “Browse” permission on the projects Wynxx must read. Organization owners/admins can adjust this in Organization → Administration → Permissions.

## SonarQube (self‑managed): Create a token

1. Sign in to your SonarQube instance (for example: [https://sonarqube.example.com](https://sonarqube.example.com)).
2. Open your avatar (top-right) → My Account → Security.
3. In the Tokens section, enter a Name (for example: Wynxx Read Token).
4. Click Generate and copy the token.

Required permissions: Your user must have “Browse” permission on each project you want Wynxx to read. An administrator can assign this in Project Settings → Permissions or at the global level in Administration → Security → Users/Groups/Permissions.

## Find your Project Key

You’ll need the Project Key to target a specific project when reading issues/vulnerabilities.

Option A (from the UI):

* Open the project in SonarCloud/SonarQube → Project Information (or Project Settings → General). The “Project Key” is displayed there.

Option B (from the URL):

* In many UIs, the project key appears in the URL query string (for example: project=your\_project\_key).

Option C (via API):

```
TerminalCode



- SonarCloud: GET https://sonarcloud.io/api/projects/search?projects=<partial_or_full_name>
- SonarQube: GET https://<your-sonarqube-host>/api/projects/search?projects=<partial_or_full_name>
```

Look for the key field in the response.

## How Wynxx authenticates

SonarCloud/SonarQube tokens are used with Basic authentication, passing the token as the username and leaving the password empty.

Example curl (macOS/Linux):

```
TerminalCode



curl -u <YOUR_TOKEN>: https://sonarcloud.io/api/authentication/validate
```

If the token is valid, you’ll receive a JSON response indicating authenticated: true.

## Verify access: list projects and vulnerabilities

Replace variables in brackets with your values. For SonarQube, use your host URL instead of sonarcloud.io.

1. List accessible projects

```
TerminalCode



curl -u <YOUR_TOKEN>: \
     "https://sonarcloud.io/api/projects/search?ps=100"
```

2. List vulnerabilities for a project

```
TerminalCode



curl -u <YOUR_TOKEN>: \
     "https://sonarcloud.io/api/issues/search?projects=<PROJECT_KEY>&types=VULNERABILITY&ps=100"
```

Notes:

* ps controls page size. Use p=2, p=3, … for pagination.
* You can filter by severities using severities=BLOCKER,CRITICAL,MAJOR.
* For SonarQube, use [https://sonarqube.example.com/api/](https://sonarqube.example.com/api/)... with the same endpoints.

## Suggested environment variables

* WYNXX\_SONAR\_HOST\_URL (for example: [https://sonarcloud.io](https://sonarcloud.io) or [https://sonarqube.example.com](https://sonarqube.example.com))
* WYNXX\_SONAR\_TOKEN
* WYNXX\_SONAR\_PROJECT\_KEY (the target project’s key)
* Optional SonarCloud organization: WYNXX\_SONAR\_ORGANIZATION

## Tips and troubleshooting

* 401 Unauthorized: The token is invalid or mistyped. Generate a new token and try again.
* 403 Forbidden or empty results: The token’s user likely lacks “Browse” access to the project(s). In SonarCloud, ensure the user is a member of the organization and the project is not restricted. In SonarQube, assign the Browse permission to the user/group on the project.
* Wrong project key: Verify the key in the project’s settings or via the projects/search API.
* Self-signed certificates (self-managed SonarQube): Use a trusted certificate on the server. Avoid disabling certificate validation; if necessary for quick tests only, curl has -k, but this is not recommended.
* Least privilege: Give only the access needed. For read-only access, do not grant analysis or admin permissions to the token’s user.
* Secret handling: Never commit tokens to source control. Store in a secret manager and rotate regularly.

Last modified on February 26, 2026

[CSV FILE Integration](/integrations/sast/Csv)[Azure DevOps Integration](/integrations/vcs/azure-devops)

On this page

* [What you will create](/integrations/sast/sonar#what-you-will-create)
* [SonarCloud: Create a token (recommended approach)](/integrations/sast/sonar#sonarcloud-create-a-token-recommended-approach)
* [SonarQube (self‑managed): Create a token](/integrations/sast/sonar#sonarqube-selfmanaged-create-a-token)
* [Find your Project Key](/integrations/sast/sonar#find-your-project-key)
* [How Wynxx authenticates](/integrations/sast/sonar#how-wynxx-authenticates)
* [Verify access: list projects and vulnerabilities](/integrations/sast/sonar#verify-access-list-projects-and-vulnerabilities)
* [Suggested environment variables](/integrations/sast/sonar#suggested-environment-variables)
* [Tips and troubleshooting](/integrations/sast/sonar#tips-and-troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/settings/cost-control
Settings

# Cost Control

This page explains how to monitor and analyze costs in the Cost Control. Track spending, view usage patterns, and monitor user activity across your organization.

## Access

* Go to Settings → Cost Control
* Access the Cost and Use Control section to monitor expenses and usage

Here you can view cost analytics and spending breakdown:

![Cost Control – Dashboard Overview](/assets/cost-dashboard-C0KHxd4m.png)

## Cost Dashboard Overview

The Cost Control dashboard provides comprehensive cost monitoring and analytics for your organization.

### Total Spent Section

**Current Spending Display:**

* **Total Amount**: Shows current spending vs. budget limit (e.g., US$ 6733.48 / 10000.00)
* **Budget Configuration**: Click the edit icon (✏️) to modify budget limits
* **Settings**: Access additional budget settings via the gear icon (⚙️)

#### Budget Configuration (Edit Icon ✏️)

When you click the edit icon, the **Budget Settings** modal opens with basic budget configuration:

![Cost Control – Budget Configuration](/assets/budget-edit-FQIf_VE2.png)

**Fields:**

* **Budget**: Set the total budget amount (e.g., $ 10,000)
* **Currency**: Select currency type (USD dropdown with other currency options)

**Actions:**

* **SAVE**: Save the new budget configuration
* **Close (X)**: Cancel and close the modal without saving

This allows you to quickly adjust your overall budget limit and currency settings.

#### Advanced Settings (Gear Icon ⚙️)

When you click the gear icon, the **Budget Settings** modal opens with advanced LLM-specific cost configurations:

![Cost Control – Advanced LLM Settings](/assets/budget-advanced-CVIUu-zp.png)

**LLM Cost Configuration:**

* **Select LLM**: Choose specific language model from dropdown
* **Cost Request ($)**: Set cost per request for the selected LLM (e.g., $ 0.00)
* **Cost Response ($)**: Set cost per response for the selected LLM (e.g., $ 0.00)

**Actions:**

* **NEW**: Create a new LLM cost configuration
* **SAVE**: Save the current configuration
* **CANCEL**: Cancel changes

**LLM Cost Table:**

* **LLM**: Lists configured language models (e.g., AwsBedRock)
* **Cost Request ($)**: Shows request cost for each LLM (e.g., $23,141.00)
* **Cost Response ($)**: Shows response cost for each LLM (e.g., $56,723.00)
* **Actions**: Edit (✏️) or Delete (🗑️) icons for each LLM configuration

This advanced interface allows granular control over costs for different AI models and services.

## Filtering and Time Controls

The dashboard provides various filtering options to analyze costs across different dimensions and time periods:

![Cost Control – Filtered Dashboard View](/assets/filtered-dashboard-CXjvqP4R.png)

**Period Filters:**

* **Today**: Current day spending and usage
* **3d**: Last 3 days of activity
* **1w**: Weekly view (selected by default)
* **Select Date Range**: Custom date range picker

**Additional Filters:**

* **LLM**: Filter by specific language models or AI services
* **Job Type**: Filter by type of processing or task

### Service Breakdown (Pie Chart)

**Service Categories:**

* **Code Documenter**: Documentation generation costs
* **Story Creator**: Story/narrative creation expenses
* **Code Dialoger**: Interactive code conversation costs
* **Code Tester**: Test generation and validation expenses
* **Code Reviewer**: Code review and analysis costs
* **Code Fixer**: Code correction and improvement expenses
* **Functional Tester**: Functional testing costs
* **Other Services**: Additional service categories

### Total Cost Monitoring (Line Chart)

**Time-based Analytics:**

* **Historical Trends**: Track spending patterns over time
* **Service Comparison**: Different colored lines for each service type
* **Date Range**: View costs across selected time periods
* **Peak Usage**: Identify high-usage periods and cost spikes

**Chart Features:**

* Interactive timeline with hover details
* Multiple service lines with color coding
* Scalable Y-axis showing cost amounts
* X-axis showing date progression (11/08 - 11/14 format)

## Top Active Users

The Top Active Users section displays detailed information about user activity and spending patterns:

![Cost Control – Top Active Users Table](/assets/top-active-users-3MssjjZH.png)

**User Activity Table:**

* **User Name**: Individual user identification
* **Request Perf**: Number of requests performed by each user
* **Range Cost**: Total cost for the selected time period
* **Cost Last Day**: Previous day's spending per user
* **Cost Last Month**: Monthly spending per user
* **Actions**: User-specific action buttons (view details, edit settings)

**Table Features:**

* Sortable columns for easy analysis
* Cost breakdown showing actual vs. limit amounts (e.g., $2416.31 / $0.00)
* Edit and view icons for user management
* Request performance metrics

#### User Budget Configuration (Edit Icon ✏️)

When you click the edit icon for a specific user, the **Budget Settings** modal opens with individual user budget configuration:

![Cost Control – User Budget Settings](/assets/user-budget-BoqZmp2o.png)

**Fields:**

* **User ID**: User identifier (e.g., "administrator") - appears to be read-only
* **User Name**: Display name for the user
* **Budget US$**: Set individual budget limit for this user (e.g., $ 232)

**Actions:**

* **SAVE**: Save the user-specific budget configuration
* **Close (X)**: Cancel and close the modal without saving

This allows you to set individual spending limits for each user, enabling granular cost control at the user level. Each user can have their own budget allocation independent of the overall organizational budget.

## Navigation and Controls

**Top Navigation:**

* **Breadcrumb**: Home → Cost and Use Control
* **Period Selection**: Quick time period buttons
* **Filter Options**: LLM and Job Type dropdowns
* **Close**: X button to exit the cost control view

## Monitoring Features

**Real-time Tracking:**

* Current spending against budget limits
* Service usage breakdown
* User activity monitoring
* Cost trend analysis

**Visual Analytics:**

* Pie chart for service distribution
* Line chart for temporal cost tracking
* Tabular data for detailed user metrics
* Interactive filtering and date selection

## User Management Integration

**Individual User Controls:**

* View individual user spending patterns
* Monitor request volumes per user
* Track cost allocation by user
* Access user-specific settings and controls

**Cost Allocation:**

* Track costs by service type
* Monitor user consumption patterns
* Analyze spending trends over time
* Identify high-usage users and services

## Tips

* **Monitor Weekly**: Use the 1w view to track regular spending patterns
* **Check Service Distribution**: Review the pie chart to identify highest-cost services
* **Track User Activity**: Monitor the top users table to identify heavy usage
* **Set Budget Limits**: Use the edit icon to configure appropriate spending limits
* **Use Date Filters**: Leverage custom date ranges for detailed analysis
* **Review Trends**: Check the line chart for unusual spending spikes

## Troubleshooting

* **Data Not Loading**: Refresh the page or check your connection
* **Incorrect Costs**: Verify the selected time period and filters
* **Missing Users**: Check if users have recent activity in the selected timeframe
* **Budget Issues**: Use the settings icon to verify budget configuration
* **Filter Problems**: Clear filters and reapply to reset the view

## Best Practices

* **Regular Monitoring**: Check cost control dashboard weekly
* **Budget Planning**: Set realistic budget limits based on usage patterns
* **User Education**: Share cost information with teams to promote awareness
* **Trend Analysis**: Use historical data to plan future budgets
* **Service Optimization**: Identify and optimize high-cost services

## Integration

Cost Control provides insights that integrate with:

* **User Control**: Individual user spending and limits
* **Group Control**: Team and department budget allocation
* **Service Management**: Feature-specific cost analysis

Learn more about user and group management:

* [User Control](/settings/user-control)
* [Group Control](/settings/group-control)

Last modified on February 26, 2026

[Application Settings](/settings/application)[Group Control](/settings/group-control)

On this page

* [Access](/settings/cost-control#access)
* [Cost Dashboard Overview](/settings/cost-control#cost-dashboard-overview)
  + [Total Spent Section](/settings/cost-control#total-spent-section)
* [Filtering and Time Controls](/settings/cost-control#filtering-and-time-controls)
  + [Service Breakdown (Pie Chart)](/settings/cost-control#service-breakdown-pie-chart)
  + [Total Cost Monitoring (Line Chart)](/settings/cost-control#total-cost-monitoring-line-chart)
* [Top Active Users](/settings/cost-control#top-active-users)
  + [User Budget Configuration (Edit Icon ✏️)](/settings/cost-control#user-budget-configuration-edit-icon-️)
* [Navigation and Controls](/settings/cost-control#navigation-and-controls)
* [Monitoring Features](/settings/cost-control#monitoring-features)
* [User Management Integration](/settings/cost-control#user-management-integration)
* [Tips](/settings/cost-control#tips)
* [Troubleshooting](/settings/cost-control#troubleshooting)
* [Best Practices](/settings/cost-control#best-practices)
* [Integration](/settings/cost-control#integration)

---


# ORIGEM: https://docs.wynxx.app/api/code-reviewer
Gft.Ai.Impact.Api.WebApi

# CodeReviewer

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/ai/review

### Request Body

* `PullRequestId`string
* `RepoName`string
* `WorkFolder`string
* `AdditionalInstructions`string
* `Files`string[]
* `JobName`string
* `RunName`string
* `TargetExtension`string
* `SearchPattern`string
* `PromptId`string
* `Llm`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `LlmTools`string[]
* `Conventions`string
* `ResponseJsonFormat`string
* `ResponseLanguage`string

### Responses

Success

string

POST /ai/review

```
curl --request POST \
  --url __API_BASE_URL__/ai/review \
  --header 'Content-Type: application/json' \
  --data '
{
  "PullRequestId": "PullRequestId",
  "RepoName": "RepoName",
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "PullRequestId": "PullRequestId",
  "RepoName": "RepoName",
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
string
```

plain

text/plainapplication/jsontext/json

---

[CodeFixer](/api/code-fixer)[CodeTester](/api/code-tester)

---


# ORIGEM: https://docs.wynxx.app/api/~schemas
Gft.Ai.Impact.Api.WebApi

# Schemas

---

## Gft.Ai.Impact.Api.WebApi.Dtos.AudienceDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.CodeLanguageDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.CostDto

* `total`number · double · readOnly
* `budget`number · double
* `groups`object | null

## Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedDto

* `period`string | null
* `total`number · double

## Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedUserItemResponseDto

* `userId`string | null
* `userName`string | null
* `requests`integer · int32
* `usage`number · double
* `budget`number · double
* `cost`number · double
* `costLastDay`number · double
* `costMonth`number · double
* `currency`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedUserResponseDto

* `costGroupedUserItems`array | null
* `pagination`object

## Gft.Ai.Impact.Api.WebApi.Dtos.JobTypeDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.LanguageDto

* `languageId`string | null
* `name`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.LlmBudgetUserRequestDto

* `id`string | null
* `userId`string | null
* `userName`string | null
* `budget`number | null · double

## Gft.Ai.Impact.Api.WebApi.Dtos.LlmCostSettingsDto

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

## Gft.Ai.Impact.Api.WebApi.Dtos.LlmDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.LlmRequestDto

* `id`string | null
* `lllmName`string | null
* `llmTypeName`string | null
* `budget`number | null · double
* `budgetPerUser`number | null · double
* `currency`string | null
* `ignoreLimit`boolean | null
* `tenantId`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.PaginationResponseDto

* `pageNumber`integer · int32
* `totalPages`integer · int32

## Gft.Ai.Impact.Api.WebApi.Dtos.PromptDto

* `id`string | null
* `name`string | null
* `description`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsLlmCostHistoryItemResponseDto

* `time`string · date-time
* `type`string | null
* `requestTokens`number · double
* `responseTokens`number · double
* `responseTime`number · double
* `llm`string | null
* `costTokenRequest`number · double
* `costTokenResponse`number · double
* `costTokenTotal`number · double

## Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsLlmCostHistoryResponseDto

* `llmCostHistoryItems`array | null
* `pagination`object

## Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsResponseDto

* `userId`string | null
* `userName`string | null
* `lastConnection`string · date-time
* `mostUsedLanguage`string | null
* `mostUsedModel`string | null
* `top3Tasks`string | null
* `totalRequestTokens`string | null
* `totalResponseTokens`string | null
* `totalTokens`string | null
* `totalDuration`string | null
* `currency`string | null
* `llmCostHistory`object

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigJobDto

* `documentationAudience`string | null
* `defaultPromptId`string | null
* `sastName`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigLLMDto

* `endPoint`string | null
* `deploymentName`string | null
* `cost`object
* `openAiKey`string | null
* `projectId`string | null
* `location`string | null
* `model`string | null
* `serviceAccountCredentialsFile`string | null
* `maxOutputTokens`string | null
* `region`string | null
* `accessKeyId`string | null
* `accessKey`string | null
* `sessionToken`string | null
* `modelId`string | null
* `maxTokens`string | null
* `langChainEndPoint`string | null
* `llmModelType`string | null
* `useRAG`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigSastDto

* `serverUrl`string | null
* `projectKey`string | null
* `authToken`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigStoryCreatorDto

* `type`string | null
* `serverUrl`string | null
* `organization`string | null
* `areaPath`string | null
* `project`string | null
* `iterationPath`string | null
* `userName`string | null
* `token`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigVcsDto

* `serverUrl`string | null
* `serverUrlApi`string | null
* `token`string | null
* `basebranch`string | null
* `apiVersion`string | null
* `project`string | null
* `serverUrlClone`string | null
* `organization`string | null
* `iterationPath`string | null
* `projectId`string | null
* `userName`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.CostDto

* `inputPerThousand`number | null · double
* `outputPerThousand`number | null · double
* `currency`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.JobsDto

* `id`string | null
* `name`string | null
* `type`string | null
* `defaultLlm`string | null
* `defaultLangChain`string | null
* `defaultVcs`string | null
* `config`object

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.LlmsDto

* `type`string | null
* `name`string | null
* `config`object
* `id`string | null
* `idLangChain`string | null
* `wizard`boolean | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.RootDto

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.SastDto

* `type`string | null
* `name`string | null
* `config`object
* `id`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.StoryCreatorDto

* `type`string | null
* `name`string | null
* `description`string | null
* `defaultLlm`string | null
* `config`object
* `id`string | null
* `baseId`string | null
* `idProjects`string | null
* `fields`array | null
* `key`string | null

## Gft.Ai.Impact.Api.WebApi.Dtos.settings.VcsDto

* `type`string | null
* `name`string | null
* `config`object
* `id`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.BaseDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationClearAllResponseDto

* `success`boolean
* `message`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationDeleteResponseDto

* `success`boolean
* `message`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationListResponse

* `items`array | null
* `current_page`integer · int32
* `page_size`integer · int32
* `total_pages`integer · int32
* `total_items`integer · int32

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationListResponseDto

* `conversation_id`string | null
* `project_id`string | null
* `timestamp`string · date-time
* `title`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationMessageListResponse

* `items`array | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ConversationMessageListResponseDto

* `message_type`string | null
* `message_content_type`string | null
* `timestamp`string · date-time
* `message`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.DocumentIngestionResultDto

* `fileName`string | null
* `fileIngested`boolean
* `message`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.Field

* `name`string | null
* `type`string | null
* `typeComponent`string | null
* `mappingFieldName`string | null
* `alias`string | null
* `mappingFieldStructure`string | null
* `mappingFielRequest`string | null
* `mappingFieldValue`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.FileListResponse

* `project_id`string | null
* `files`array | null

## Gft.Ai.Impact.Application.WebApi.Dtos.FileListResponseDto

* `file_name`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.GroupDto

* `id`string | null
* `name`string | null
* `parentId`string | null
* `path`string | null
* `subGroups`array | null
* `roleIds`array | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ProcessDefinitionConfig

* `level`string | null
* `mappingFieldName`string | null
* `levelAlias`string | null
* `defaultPromptId`string | null
* `fields`array | null
* `children`array | null
* `issueType`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ProjectDto

* `id`string | null
* `name`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ProjectListRequestDto

* `id`string | null
* `projectName`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.ResetResponseDto

* `success`boolean
* `message`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.UseCaseDto

* `projectId`string | null
* `levelTypeId`string | null
* `requestTitle`string | null
* `requestDescription`string | null
* `jobId`string | null
* `id`string | null
* `modificationAt`string · date-time

## Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemTreeDto

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

## Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemUpdateDto

* `id`string | null
* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `parentId`string | null
* `useCaseId`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `integrated`boolean | null
* `jobId`string | null
* `externalId`string | null
* `fields`array | null
* `parent`array | null

## Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemUpdateField

* `alias`string | null
* `type`string | null
* `name`string | null
* `value`string | null
* `values`array | null

## Gft.Ai.Impact.Contracts.Job.BaseJobResponse

* `additionalResultFiles`array | null · readOnly
* `start`string | null · date-time
* `end`string | null · date-time
* `inputToken`integer · int32
* `outputToken`integer · int32
* `inputToOutputRatio`number | null · double · readOnly
* `duration`object
* `errors`object | null · readOnly
* `totalItemCount`integer · int32
* `processedItemCount`integer · int32
* `results`array | null · readOnly
* `processingItem`object
* `status`string · enum

  Enum values: 

  NotStarted

  Running

  Failed

  Cancelled

  CancellationRequested

  Completed

  CompletedWithErrors

## Gft.Ai.Impact.Contracts.Job.BaseJobResponse+JobResultItem

* `sourceItem`string | null
* `output`array | null

## Gft.Ai.Impact.Contracts.Job.BaseJobResponse+JobResultItem+Content

* `targetItem`string | null
* `status`string · enum

  Enum values: 

  NotStarted

  Processing

  Error

  Done

  Skipped
* `uri`string | null · uri
* `message`string | null
* `inputToken`integer · int32
* `outputToken`integer · int32

## Gft.Ai.Impact.Contracts.Job.JobStatus

string · enum

Enum values: 

NotStarted

Running

Failed

Cancelled

CancellationRequested

Completed

CompletedWithErrors

## Gft.Ai.Impact.Contracts.Job.VcsRepository

* `name`string | null
* `title`string | null
* `folderToUpdate`string | null
* `folderToMonitor`string | null
* `referenceIdLeft`string | null
* `referenceIdRight`string | null
* `referenceFromDate`string | null · date-time
* `referenceToDate`string | null · date-time

## Gft.Ai.Impact.Contracts.Job.WorkspaceItem+WorkspaceItemContent+ProcessStatus

string · enum

Enum values: 

NotStarted

Processing

Error

Done

Skipped

## Gft.Ai.Impact.Contracts.Settings.BaseSettings

* `id`string | null
* `createdAt`string · date-time
* `updatedAt`string · date-time
* `type`string · enum

  Enum values: 

  Jobs

  Llms

  Sast

  Vcs

  Repositories

  Agile

  UserControl
* `subType`string · enum

  Enum values: 

  DocCreator

  TestCreator

  CodeFixer

  CodeReviewer

  AgileJob

  Developer

  FuncionalTest

  OpenAi
* `key`string | null
* `name`string | null
* `nameExtension`string | null
* `fields`array | null

## Gft.Ai.Impact.Contracts.Settings.ConfigurationSubType

string · enum

Enum values: 

DocCreator

TestCreator

CodeFixer

CodeReviewer

AgileJob

Developer

FuncionalTest

OpenAi

## Gft.Ai.Impact.Contracts.Settings.ConfigurationType

string · enum

Enum values: 

Jobs

Llms

Sast

Vcs

Repositories

Agile

UserControl

## Gft.Ai.Impact.Contracts.Settings.Dto.BaseSettingsDto

* `id`string | null
* `type`string | null
* `subType`string | null
* `name`string | null
* `fields`array | null

## Gft.Ai.Impact.Contracts.Settings.Dto.BaseSettingsField

* `alias`string | null
* `type`string | null
* `name`string | null
* `value`string | null
* `values`array | null
* `subFields`array | null

## Gft.Ai.Impact.Contracts.Settings.Field

* `fieldName`string | null
* `fieldType`string · enum

  Enum values: 

  String

  Password

  Number

  Boolean

  Object

  ArrayObject
* `value` | null
* `subFields`array | null

## Gft.Ai.Impact.Contracts.Settings.FieldType

string · enum

Enum values: 

String

Password

Number

Boolean

Object

ArrayObject

## LlmProvider

string · enum

Enum values: 

GOOGLE

ANTHROPIC

## System.TimeSpan

* `ticks`integer · int64
* `days`integer · int32 · readOnly
* `hours`integer · int32 · readOnly
* `milliseconds`integer · int32 · readOnly
* `microseconds`integer · int32 · readOnly
* `nanoseconds`integer · int32 · readOnly
* `minutes`integer · int32 · readOnly
* `seconds`integer · int32 · readOnly
* `totalDays`number · double · readOnly
* `totalHours`number · double · readOnly
* `totalMilliseconds`number · double · readOnly
* `totalMicroseconds`number · double · readOnly
* `totalNanoseconds`number · double · readOnly
* `totalMinutes`number · double · readOnly
* `totalSeconds`number · double · readOnly

On this page

* [Gft.Ai.Impact.Api.WebApi.Dtos.AudienceDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-audience-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.CodeLanguageDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-code-language-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.CostDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-cost-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-cost-grouped-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedUserItemResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-cost-grouped-user-item-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.CostGroupedUserResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-cost-grouped-user-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.JobTypeDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-job-type-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.LanguageDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-language-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.LlmBudgetUserRequestDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-llm-budget-user-request-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.LlmCostSettingsDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-llm-cost-settings-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.LlmDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-llm-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.LlmRequestDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-llm-request-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.PaginationResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-pagination-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.PromptDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-prompt-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsLlmCostHistoryItemResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-user-cost-details-llm-cost-history-item-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsLlmCostHistoryResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-user-cost-details-llm-cost-history-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.UserCostDetailsResponseDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-user-cost-details-response-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigJobDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-config-job-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigLLMDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-config-llm-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigSastDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-config-sast-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigStoryCreatorDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-config-story-creator-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.ConfigVcsDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-config-vcs-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.CostDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-cost-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.JobsDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-jobs-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.LlmsDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-llms-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.RootDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-root-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.SastDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-sast-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.StoryCreatorDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-story-creator-dto)
* [Gft.Ai.Impact.Api.WebApi.Dtos.settings.VcsDto](/api/~schemas#gft-ai-impact-api-web-api-dtos-settings-vcs-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.BaseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-base-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationClearAllResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-clear-all-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationDeleteResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-delete-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationListResponse](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-list-response)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationListResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-list-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationMessageListResponse](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-message-list-response)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ConversationMessageListResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-conversation-message-list-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.DocumentIngestionResultDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-document-ingestion-result-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.Field](/api/~schemas#gft-ai-impact-application-web-api-dtos-field)
* [Gft.Ai.Impact.Application.WebApi.Dtos.FileListResponse](/api/~schemas#gft-ai-impact-application-web-api-dtos-file-list-response)
* [Gft.Ai.Impact.Application.WebApi.Dtos.FileListResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-file-list-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.GroupDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-group-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ProcessDefinitionConfig](/api/~schemas#gft-ai-impact-application-web-api-dtos-process-definition-config)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ProjectDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-project-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ProjectListRequestDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-project-list-request-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.ResetResponseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-reset-response-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.UseCaseDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-use-case-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemTreeDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-work-item-tree-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemUpdateDto](/api/~schemas#gft-ai-impact-application-web-api-dtos-work-item-update-dto)
* [Gft.Ai.Impact.Application.WebApi.Dtos.WorkItemUpdateField](/api/~schemas#gft-ai-impact-application-web-api-dtos-work-item-update-field)
* [Gft.Ai.Impact.Contracts.Job.BaseJobResponse](/api/~schemas#gft-ai-impact-contracts-job-base-job-response)
* [Gft.Ai.Impact.Contracts.Job.BaseJobResponse+JobResultItem](/api/~schemas#gft-ai-impact-contracts-job-base-job-response-job-result-item)
* [Gft.Ai.Impact.Contracts.Job.BaseJobResponse+JobResultItem+Content](/api/~schemas#gft-ai-impact-contracts-job-base-job-response-job-result-item-content)
* [Gft.Ai.Impact.Contracts.Job.JobStatus](/api/~schemas#gft-ai-impact-contracts-job-job-status)
* [Gft.Ai.Impact.Contracts.Job.VcsRepository](/api/~schemas#gft-ai-impact-contracts-job-vcs-repository)
* [Gft.Ai.Impact.Contracts.Job.WorkspaceItem+WorkspaceItemContent+ProcessStatus](/api/~schemas#gft-ai-impact-contracts-job-workspace-item-workspace-item-content-process-status)
* [Gft.Ai.Impact.Contracts.Settings.BaseSettings](/api/~schemas#gft-ai-impact-contracts-settings-base-settings)
* [Gft.Ai.Impact.Contracts.Settings.ConfigurationSubType](/api/~schemas#gft-ai-impact-contracts-settings-configuration-sub-type)
* [Gft.Ai.Impact.Contracts.Settings.ConfigurationType](/api/~schemas#gft-ai-impact-contracts-settings-configuration-type)
* [Gft.Ai.Impact.Contracts.Settings.Dto.BaseSettingsDto](/api/~schemas#gft-ai-impact-contracts-settings-dto-base-settings-dto)
* [Gft.Ai.Impact.Contracts.Settings.Dto.BaseSettingsField](/api/~schemas#gft-ai-impact-contracts-settings-dto-base-settings-field)
* [Gft.Ai.Impact.Contracts.Settings.Field](/api/~schemas#gft-ai-impact-contracts-settings-field)
* [Gft.Ai.Impact.Contracts.Settings.FieldType](/api/~schemas#gft-ai-impact-contracts-settings-field-type)
* [LlmProvider](/api/~schemas#llm-provider)
* [System.TimeSpan](/api/~schemas#system-time-span)

---


# ORIGEM: https://docs.wynxx.app/getting-started/faq
Getting Started

# FAQ (Frequently Asked Questions)

This page covers the most common questions, issues, and solutions for Wynxx installation, configuration, and operation.

---

## Version and Installation

### Which version should I install?

* **New installations**: Always use the latest stable version recommended by GFT (currently 3.0.x via Installer).
* **Existing environments**: Version 2.x environments should use 2.1.3 for subscription renewal. New projects should start on 3.0.
* **Subscription**: Request the `subscription.zip` from the Wynxx/GFT team, compatible with your version.
* **Release Notes**: Always consult before installing or updating.

### Can I use MongoDB Atlas?

No. Currently, only self-hosted or containerized MongoDB is supported to ensure maximum compatibility and performance.

---

## Common Issues and Solutions

### Image Pull Errors

**Cause**: Invalid `imagePullSecret` or expired credentials.

**Solution**:

1. Re-login to the ACR registry
2. Update the Kubernetes secret
3. Verify the correct namespace is being used

```
TerminalCode



# Check imagePullSecrets
kubectl get pods -o jsonpath='{.items[*].spec.imagePullSecrets}' -n wynxx

# Recreate secret if needed
kubectl create secret docker-registry acr-secret \
  --docker-server=gftai.azurecr.io \
  --docker-username=<username> \
  --docker-password=<password> \
  -n wynxx
```

### Pods Not Starting / CrashLoopBackOff

**Cause**: Insufficient resources, errors in `override-values.yaml`, or missing dependencies (MongoDB, Redis, RabbitMQ).

**Solution**:

1. Check pod logs: `kubectl logs <pod-name> -n wynxx`
2. Verify required services are running
3. Review resource requests/limits in your values file

### DNS / Ingress Problems

**Cause**: DNS not propagated, incorrect IP, or missing Ingress configuration.

**Solution**:

1. Validate DNS resolution: `nslookup your-domain.com`
2. Verify ingress-nginx is running: `kubectl get pods -n ingress-nginx`
3. Check Ingress resources: `kubectl get ingress -n wynxx`

### Keycloak Authentication Errors (SSO/MFA)

**Cause**: Inconsistent realm, client, roles, or secret configuration.

**Solution**:

1. Verify realm settings match your `override-values.yaml`
2. Check client ID and secret are correct
3. Ensure proper roles are assigned to users

### "Network is unreachable" Errors

**Cause**: Blocked egress, NetworkPolicy restrictions, or firewall/proxy issues.

**Solution**:

1. Test connectivity from inside the pod:

```
TerminalCode



kubectl exec -it <pod-name> -n wynxx -- curl -v https://api.openai.com
```

2. Review NetworkPolicy rules
3. Check firewall/proxy configuration

### GitHub/GitLab/Azure Token Denied

**Cause**: Expired token, incorrect permissions, or using SSO instead of PAT.

**Solution**:

1. Generate a new Personal Access Token (PAT)
2. Verify required scopes are granted
3. For GitLab with SAML: Use PAT, not SSO tokens

### SAST Integration Failures (SonarQube/SonarCloud)

**Cause**: Incorrect URL, token, or report format.

**Solution**:

1. Test API connectivity directly:

```
TerminalCode



curl -H "Authorization: Bearer <token>" https://sonarcloud.io/api/projects/search
```

2. Verify project key and report configuration

### 502 Bad Gateway on Settings

**Cause**: Required pods unavailable or `override-values.yaml` mismatch.

**Solution**:

1. Check all required pods are running: `kubectl get pods -n wynxx`
2. Review pod logs for errors
3. Verify override configuration matches deployment

### CostControl Not Registering Tokens

**Cause**: `llm_service_enable` disabled or incorrect endpoint.

**Solution**: Ensure these settings in your configuration:

```
YAMLCode



llm_service_enable: "True"
llm_service_url_base: "http://ai-impact-backend-service:80"
```

---

## LLM Configuration

### What LLM models are supported?

Wynxx uses a **Multi-LLM** architecture compatible with:

| Provider | Recommended Models |
| --- | --- |
| **OpenAI** | GPT-4o, GPT-4 |
| **Azure OpenAI** | GPT-4o, GPT-4 |
| **Google Gemini** | Pro 1.5 |
| **Anthropic Claude** | 3.5 Sonnet |
| **AWS Bedrock** | Claude, Titan |

> **Tip**: You can switch models at any time from AI settings to compare results.

### When should I use LiteLLM?

LiteLLM is an open-source gateway compatible with the OpenAI API for multiple LLMs.

**Use cases**:

* Expose multiple LLM providers through a single API
* Advanced logging, quotas, fallback, and cost control
* Testing and homologation environments

**Configuration**:

```
YAMLCode



# override-values.yaml
litellm:
  enabled: true
```

In Wynxx Settings:

* Type: OpenAI
* URL: Your LiteLLM endpoint
* API Key: LiteLLM API key
* Model: As configured in LiteLLM

### CodeFixer producing unexpected output?

**Cause**: Using a non-homologated LLM model.

**Solution**: Switch to GPT-4 or update subscription for new model support.

---

## Integrations

### What external access (egress) is required?

| Integration | Purpose |
| --- | --- |
| LLM Providers | OpenAI, Azure, Gemini, Bedrock APIs |
| Git Repositories | GitHub, GitLab, Azure DevOps |
| Agile Boards | Jira, Azure Boards |
| SAST Tools | SonarQube, SonarCloud |

### Is Jira/Azure synchronization real-time?

Yes. Changes made in Wynxx are reflected instantly in Jira/Azure and vice versa.

### What if my Jira project has custom fields?

Wynxx automatically maps standard fields. Custom fields can be configured manually in **Settings → Agile**.

### Board synchronization errors (Jira/Azure)?

**Cause**: Custom fields or ProcessDefinition mismatch.

**Solution**: Review field mappings in Settings and ensure ProcessDefinition matches your board configuration.

---

## Backup and Restore

### How do I back up my data?

| Component | Backup Method |
| --- | --- |
| MongoDB | `mongodump` / `mongorestore` |
| Redis | Snapshots |
| RabbitMQ | Export definitions |

> **Important**: Always backup MongoDB before any update.

### What's needed for a full restore?

1. MongoDB backup
2. `override-values.yaml` file
3. All secrets and credentials
4. `subscription.zip`

---

## Subscription and Updates

### How do I apply a new subscription?

1. Obtain the new `subscription.zip` from GFT
2. Apply to your cluster
3. **Restart all pods** for changes to take effect

```
TerminalCode



kubectl rollout restart deployment -n wynxx
```

### Can I customize the container image for internal certificates?

Yes, using a custom Dockerfile. Note that you are responsible for keeping the image updated with each new Wynxx release.

---

## Troubleshooting Checklist

When opening a support ticket, always include:

* Wynxx version (tag)
* `override-values.yaml` (without secrets)
* Pod logs: `kubectl logs <pod> -n wynxx`
* Pod status: `kubectl get pods -n wynxx`
* Failed endpoint/payload
* Curl test from inside the pod
* UI screenshots/logs

---

## Best Practices

1. **Restart pods** after changing credentials or configuration
2. **Version control** your `override-values.yaml`
3. **Backup MongoDB** before any update
4. **Rotate tokens** regularly
5. **Don't customize** the core application
6. **Validate integrations** after every update

---

## Quick Troubleshooting Reference

| Issue Type | Action |
| --- | --- |
| Authentication | Validate token, roles, endpoint |
| Network | Curl from inside pod, check NetworkPolicy |
| Integration | Test API in isolation |
| Update | Backup before, validate restore |
| Environment won't start | Check resources, secrets, dependencies |

---

## Post-Installation Validation

Always test after installation:

1. **Health check**: `curl https://your-domain/health`
2. **Auth check**: `curl https://your-domain/auth`
3. **LLM test**: Via curl or Postman
4. **Feature test**: Via API or UI

---

## See Also

* [Prerequisites](/installation/prerequisites)
* [Installation Overview](/installation)
* [Troubleshooting Guide](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[What is Wynxx?](/introduction)[Overview](/installation)

On this page

* [Version and Installation](/getting-started/faq#version-and-installation)
  + [Which version should I install?](/getting-started/faq#which-version-should-i-install)
  + [Can I use MongoDB Atlas?](/getting-started/faq#can-i-use-mongodb-atlas)
* [Common Issues and Solutions](/getting-started/faq#common-issues-and-solutions)
  + [Image Pull Errors](/getting-started/faq#image-pull-errors)
  + [Pods Not Starting / CrashLoopBackOff](/getting-started/faq#pods-not-starting--crashloopbackoff)
  + [DNS / Ingress Problems](/getting-started/faq#dns--ingress-problems)
  + [Keycloak Authentication Errors (SSO/MFA)](/getting-started/faq#keycloak-authentication-errors-ssomfa)
  + ["Network is unreachable" Errors](/getting-started/faq#network-is-unreachable-errors)
  + [GitHub/GitLab/Azure Token Denied](/getting-started/faq#githubgitlabazure-token-denied)
  + [SAST Integration Failures (SonarQube/SonarCloud)](/getting-started/faq#sast-integration-failures-sonarqubesonarcloud)
  + [502 Bad Gateway on Settings](/getting-started/faq#502-bad-gateway-on-settings)
  + [CostControl Not Registering Tokens](/getting-started/faq#costcontrol-not-registering-tokens)
* [LLM Configuration](/getting-started/faq#llm-configuration)
  + [What LLM models are supported?](/getting-started/faq#what-llm-models-are-supported)
  + [When should I use LiteLLM?](/getting-started/faq#when-should-i-use-litellm)
  + [CodeFixer producing unexpected output?](/getting-started/faq#codefixer-producing-unexpected-output)
* [Integrations](/getting-started/faq#integrations)
  + [What external access (egress) is required?](/getting-started/faq#what-external-access-egress-is-required)
  + [Is Jira/Azure synchronization real-time?](/getting-started/faq#is-jiraazure-synchronization-real-time)
  + [What if my Jira project has custom fields?](/getting-started/faq#what-if-my-jira-project-has-custom-fields)
  + [Board synchronization errors (Jira/Azure)?](/getting-started/faq#board-synchronization-errors-jiraazure)
* [Backup and Restore](/getting-started/faq#backup-and-restore)
  + [How do I back up my data?](/getting-started/faq#how-do-i-back-up-my-data)
  + [What's needed for a full restore?](/getting-started/faq#whats-needed-for-a-full-restore)
* [Subscription and Updates](/getting-started/faq#subscription-and-updates)
  + [How do I apply a new subscription?](/getting-started/faq#how-do-i-apply-a-new-subscription)
  + [Can I customize the container image for internal certificates?](/getting-started/faq#can-i-customize-the-container-image-for-internal-certificates)
* [Troubleshooting Checklist](/getting-started/faq#troubleshooting-checklist)
* [Best Practices](/getting-started/faq#best-practices)
* [Quick Troubleshooting Reference](/getting-started/faq#quick-troubleshooting-reference)
* [Post-Installation Validation](/getting-started/faq#post-installation-validation)
* [See Also](/getting-started/faq#see-also)

---


# ORIGEM: https://docs.wynxx.app/guides/features/code-documenter
Features

# Code Documenter

The Code Documenter helps you turn source code into clear, structured documentation in minutes. This guide mirrors the in‑app tutorial and shows the exact steps to go from code to a polished document.

## When to use it

* Add documentation to existing projects that lack comments or READMEs
* Explain complex functions, classes, or architectural decisions
* Create onboarding material for new team members
* Keep docs in sync after refactors or feature additions

## Step‑by‑step tutorial

### 1) Configure parameters

Open the Code Documenter and go to the Parameters section. Set the following options:

* Source Language: choose the language of your source code (or Auto‑detect when unsure)
* Prompt: select DocumentCode\_V5 (recommended)
* Persona: define who will read the docs (e.g., backend developers, QA, product managers)
* LLM: choose the language model available in your workspace
* Output Language: pick the language for the generated documentation

Add your content in one of two ways:

* Paste your code directly into the Description field, or
* Drag and drop a file into the dropzone

![Wynxx documenter Parameters](/assets/parameters-CvUlmRN4.png)

Tips

* Prefer a single, self‑contained unit (a module or a few related functions) per run
* If pasting long files, focus on the most relevant section first for faster previews

#### Prompt options

Choose a prompt based on your documentation goal. As a general rule, for end‑to‑end source code documentation in multiple languages, use DocumentCode\_V5 (recommended). For other scenarios, see the table below:

| Prompt name | What it does |
| --- | --- |
| Architecture\_Level1\_Json | Analyzes code and produces an architecture description in JSON format |
| Architecture\_Level1\_Json\_Chain | Performs architecture analysis in chained steps, outputting JSON |
| Architecture\_MainTypes | Identifies main software components and their relationships |
| Architecture\_Meaning | Produces a short executive summary of the software’s purpose |
| Architecture\_Meaning\_Detailed | Generates a detailed explanation of the system’s functionality |
| Architecture\_Summary | Creates a high‑level architecture document, optionally with diagrams |
| DocumentCode\_Chain\_V1 | Generates documentation in a chained/multi‑step process |
| DocumentCode\_Chain\_V2 | Improved chained documentation generation |
| DocumentCode\_CLI | Produces detailed Markdown docs for command‑line apps (commands, flags, examples) |
| DocumentCode\_V3 | Writes meaningful docs with optional sections and diagrams |
| DocumentCode\_V3\_AzureDevops | Tailors documentation specifically for Azure DevOps projects |
| DocumentCode\_V4 | Creates concise docs without referring to external context |
| DocumentCode\_V5 | Multilingual documentation generator with an Insights section (recommended) |
| DocumentCode\_VPY\_AzureDevops | Python‑focused documentation tuned for Azure DevOps workflows |
| ReleaseNotes\_Summary\_V1 | Produces a high‑level release notes summary |
| ReleaseNotes\_V1 | Generates detailed release notes from git diff outputs |
| SummaryDocument\_V1 | Creates a stakeholder‑friendly documentation summary for a target persona |

### 2) Preview the result

Click Preview to generate a draft. Use this to check:

* Are the explanations accurate for the target audience?
* Do the names, parameters, and return values read correctly?
* Is the Output Language what you expect?

If the preview isn’t what you need, adjust parameters (Persona, Prompt, or Language) and preview again.

![Wynxx documenter Preview](/assets/preview-D7PO1ReO.png)

### 3) Generate and export

When you’re happy with the preview, proceed to generate the final document.

After generation you can typically:

* Copy the documentation to your clipboard
* Download it (for example, as Markdown) depending on your workspace configuration
* Save or share it using the options exposed in your environment

Note: Available export actions may vary based on your workspace settings and integrations.

## What gets generated

Depending on your input and settings, the output may include:

* API‑style documentation for functions, classes, and methods
* Inline explanations for complex logic and edge cases
* High‑level overview (purpose, constraints, assumptions)
* Usage examples and small code snippets

## Example

Input (JavaScript)

```
JavascriptCode



// price-utils.js
export function calculateTotalPrice(basePrice, taxRate, discountPercent) {
  if (basePrice < 0) throw new Error("basePrice must be >= 0");
  const discount = (basePrice * (discountPercent ?? 0)) / 100;
  const subtotal = basePrice - discount;
  const tax = subtotal * (taxRate ?? 0);
  return Number((subtotal + tax).toFixed(2));
}
```

Possible output (excerpt)

```
MarkdownCode



# Documenter: Calculate Total Price Function

## Overview

This function calculates the total price of an item by considering its base price, applicable tax rate, and discount percentage. It ensures that the base price is a positive value and applies the discount and tax calculations accordingly.

## Process Flow

:::mermaid
flowchart TD
    A("Start") --> B["Check if basePrice > 0"]
    B -->|No| C["Throw Error: 'basePrice must be > 0'"]
    B -->|Yes| D["Calculate Discount"]
    D --> E["Calculate Subtotal"]
    E --> F["Calculate Tax"]
    F --> G["Calculate Total Price"]
    G --> H("End")
:::

## Insights

- The function ensures that the base price is greater than zero before proceeding with calculations.
- It calculates the discount based on the base price and discount percentage.
- The subtotal is derived by subtracting the discount from the base price.
- Tax is calculated on the subtotal using the provided tax rate.
- The final total price is rounded to two decimal places for precision.

## Dependencies
```

## Best practices

* Be explicit about Persona so tone and depth match readers (e.g., devs vs. PMs)
* Prefer smaller, cohesive inputs for higher quality summaries
* Adopt a consistent naming convention and comment style in source files
* Keep generated docs alongside your code and update them after refactors

## Troubleshooting

* Output is too generic

  + Switch to the DocumentCode\_V5 prompt (recommended) and specify persona
  + Provide a smaller, focused code sample or add brief inline comments first
* Some parts are missing

  + Confirm the pasted code compiles or is syntactically valid
  + For large files, document critical sections in separate runs
* Wrong language in the result

  + Check Output Language in Parameters and re‑generate

## Related features

* [Code Reviewer](/api-guides/code-reviewer)
* [Code Dialoguer](/guides/features/code-dialoguer)
* [Story Creator](/guides/features/story-creator)

Last modified on February 26, 2026

[Code Dialoguer](/guides/features/code-dialoguer)[Code Fixer](/guides/features/code-fixer)

On this page

* [When to use it](/guides/features/code-documenter#when-to-use-it)
* [Step‑by‑step tutorial](/guides/features/code-documenter#stepbystep-tutorial)
  + [1) Configure parameters](/guides/features/code-documenter#1-configure-parameters)
  + [2) Preview the result](/guides/features/code-documenter#2-preview-the-result)
  + [3) Generate and export](/guides/features/code-documenter#3-generate-and-export)
* [What gets generated](/guides/features/code-documenter#what-gets-generated)
* [Example](/guides/features/code-documenter#example)
* [Best practices](/guides/features/code-documenter#best-practices)
* [Troubleshooting](/guides/features/code-documenter#troubleshooting)
* [Related features](/guides/features/code-documenter#related-features)

---


# ORIGEM: https://docs.wynxx.app/api/agile
Gft.Ai.Impact.Api.WebApi

# Agile

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/preview

### Request Body

* `LevelTypeId`string
* `ProjectId`string
* `RequestTitle`string
* `RequestDescription`string
* `ParentId`string
* `UseCaseId`string
* `WorkItemId`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/preview

```
curl --request POST \
  --url __API_BASE_URL__/bff/agile/preview \
  --header 'Content-Type: application/json' \
  --data '
{
  "LevelTypeId": "LevelTypeId",
  "ProjectId": "ProjectId",
  "RequestTitle": "RequestTitle",
  "RequestDescription": "RequestDescription",
  "ParentId": "ParentId",
  "UseCaseId": "UseCaseId",
  "WorkItemId": "WorkItemId",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "JobName": "JobName",
  "RunName": "RunName",
  "AdditionalInstructions": "AdditionalInstructions",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "Conventions": "Conventions",
  "SystemPrompt.Id": "SystemPrompt.Id",
  "SystemPrompt.Content": "SystemPrompt.Content",
  "SystemPrompt.JobType": "SystemPrompt.JobType",
  "SystemPrompt.Description": "SystemPrompt.Description",
  "InputFolder": "InputFolder",
  "OutputFolder": "OutputFolder",
  "SearchPattern": "SearchPattern",
  "TargetExtension": "TargetExtension",
  "LlmTools": [
    "string"
  ],
  "ResponseJsonFormat": "ResponseJsonFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "UserId": "UserId",
  "UserName": "UserName",
  "JobType": "JobType",
  "IsBudgetByUser": true,
  "LlmType": "LlmType",
  "EncodingType": "EncodingType",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "LevelTypeId": "LevelTypeId",
  "ProjectId": "ProjectId",
  "RequestTitle": "RequestTitle",
  "RequestDescription": "RequestDescription",
  "ParentId": "ParentId",
  "UseCaseId": "UseCaseId",
  "WorkItemId": "WorkItemId",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "JobName": "JobName",
  "RunName": "RunName",
  "AdditionalInstructions": "AdditionalInstructions",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "Conventions": "Conventions",
  "SystemPrompt.Id": "SystemPrompt.Id",
  "SystemPrompt.Content": "SystemPrompt.Content",
  "SystemPrompt.JobType": "SystemPrompt.JobType",
  "SystemPrompt.Description": "SystemPrompt.Description",
  "InputFolder": "InputFolder",
  "OutputFolder": "OutputFolder",
  "SearchPattern": "SearchPattern",
  "TargetExtension": "TargetExtension",
  "LlmTools": [
    "string"
  ],
  "ResponseJsonFormat": "ResponseJsonFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "UserId": "UserId",
  "UserName": "UserName",
  "JobType": "JobType",
  "IsBudgetByUser": true,
  "LlmType": "LlmType",
  "EncodingType": "EncodingType",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
string
```

plain

text/plainapplication/jsontext/json

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/re-create

### Request Body

* `LevelTypeId`string
* `ProjectId`string
* `RequestTitle`string
* `RequestDescription`string
* `ParentId`string
* `UseCaseId`string
* `WorkItemId`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/re-create

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/story-points

### Request Body

* `WorkItemID`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/story-points

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/list

### Responses

Success

* `level`string | null
* `mappingFieldName`string | null
* `levelAlias`string | null
* `defaultPromptId`string | null
* `fields`array | null
* `children`array | null · `children (circular)`
* `issueType`string | null

GET /bff/agile/process-definition/list

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/{JobId}

### path Parameters

* `JobId`string · required · style: simple

### Responses

Success

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

GET /bff/agile/process-definition/{JobId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition/{level}/next

### path Parameters

* `level`string · required · style: simple

### Responses

Success

No data returned

GET /bff/agile/process-definition/{level}/next

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/process-definition-sync-levels-totalizer/{JobId}

### path Parameters

* `JobId`string · required · style: simple

### Responses

Success

No data returned

GET /bff/agile/process-definition-sync-levels-totalizer/{JobId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/user-case/list

### Responses

Success

* `projectId`string | null
* `levelTypeId`string | null
* `requestTitle`string | null
* `requestDescription`string | null
* `jobId`string | null
* `id`string | null
* `modificationAt`string · date-time

GET /bff/agile/user-case/list

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

string

DELETE /bff/agile/work-item/{workItemID}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}/detail

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

* `id`string | null
* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `parentId`string | null
* `useCaseId`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `integrated`boolean | null
* `jobId`string | null
* `externalId`string | null
* `fields`array | null
* `parent`array | null · `parent (circular)`

GET /bff/agile/work-item/{workItemID}/detail

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{workItemID}/lock

### path Parameters

* `workItemID`string · required · style: simple

### Responses

Success

boolean

PUT /bff/agile/work-item/{workItemID}/lock

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/create

### Request Body

* `Id`string
* `IdProjects`string
* `ProjectId`string
* `LevelTypeId`string
* `LevelAliasTypeId`string
* `LevelTypeIdNext`string[]
* `LevelTypeIdAliasNext`string[]
* `ParentId`string
* `UseCaseId`string
* `Order`integer · int32
* `Blocked`boolean
* `Integrated`boolean
* `JobId`string
* `ExternalId`string
* `Fields`object[]
* `Parent`object[]

### Responses

Success

* `id`string | null
* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `parentId`string | null
* `useCaseId`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `integrated`boolean | null
* `jobId`string | null
* `externalId`string | null
* `fields`array | null
* `parent`array | null · `parent (circular)`

POST /bff/agile/work-item/create

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/edit

### Request Body

* `Id`string
* `IdProjects`string
* `ProjectId`string
* `LevelTypeId`string
* `LevelAliasTypeId`string
* `LevelTypeIdNext`string[]
* `LevelTypeIdAliasNext`string[]
* `ParentId`string
* `UseCaseId`string
* `Order`integer · int32
* `Blocked`boolean
* `Integrated`boolean
* `JobId`string
* `ExternalId`string
* `Fields`object[]
* `Parent`object[]

### Responses

Success

string

PUT /bff/agile/work-item/edit

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/work-item/{LevelType}/config

### path Parameters

* `LevelType`string · required · style: simple

### Responses

Success

* `alias`string | null
* `type`string | null
* `name`string | null
* `value`string | null
* `values`array | null

GET /bff/agile/work-item/{LevelType}/config

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/agile/sync-workitems

### Request Body

* `ProjectId`string
* `UseCaseId`string
* `BackLogServiceName`string
* `PromptId`string
* `Llm`string
* `JobName`string
* `RunName`string
* `AdditionalInstructions`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `Conventions`string
* `SystemPrompt.Id`string
* `SystemPrompt.Content`string
* `SystemPrompt.JobType`string
* `SystemPrompt.Description`string
* `InputFolder`string
* `OutputFolder`string
* `SearchPattern`string
* `TargetExtension`string
* `LlmTools`string[]
* `ResponseJsonFormat`string
* `SourceCodeLanguage`string
* `CsvFileList`array[]
* `UserId`string
* `UserName`string
* `JobType`string
* `IsBudgetByUser`boolean
* `LlmType`string
* `EncodingType`string
* `ResponseLanguage`string

### Responses

Success

string

POST /bff/agile/sync-workitems

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/agile/re-ordem

### Request Body

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

### Responses

Success

* `idProjects`string | null
* `projectId`string | null
* `levelTypeId`string | null
* `levelAliasTypeId`string | null
* `levelTypeIdPrevious`string | null
* `levelTypeIdNext`array | null
* `levelTypeIdAliasNext`array | null
* `name`string | null
* `order`integer | null · int32
* `blocked`boolean | null
* `children`array | null · `children (circular)`
* `id`string | null
* `idParent`string | null
* `useCaseId`string | null
* `externalId`string | null

PUT /bff/agile/re-ordem

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/level/{levelId}

### path Parameters

* `levelId`string · required · style: simple

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/agile/level/{levelId}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/agile/project/list

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/agile/project/list

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/agile/tree-by-usecase/{useCaseId}

### path Parameters

* `useCaseId`string · required · style: simple

### Responses

Success

string

DELETE /bff/agile/tree-by-usecase/{useCaseId}

---

[Ai](/api/ai)

---


# ORIGEM: https://docs.wynxx.app/guides/features/story-creator2
Features

# Story Creator 2.0

The Story Creator 2.0 is a comprehensive project management tool that helps you organize projects, manage backlogs, and create user personas. This updated interface provides a modern, intuitive experience for managing your development workflow.

## Overview

Story Creator 2.0 introduces a new interface design with two main management areas:

* **Projects**: Manage and organize your projects with external tool integrations
* **Personas**: Create and manage user personas for your development process

## Getting Started

### Accessing Story Creator

When you first access Story Creator 2.0, you'll see the main dashboard with two primary options:

![Story Creator 2.0 Home](/assets/story-creator-home-DrviHAu7.png)

Select what you want to manage:

* **Projects**: To manage projects and integrations with external tools
* **Personas**: To create and manage user personas

## Managing Projects

Our Wynxx platform allows you to centralize the management of your initiatives through two project types available in the dropdown menu.

### Available Project Types

#### 1. Default

Standard project type with simplified configuration for quick setup.

* **Purpose:** Basic project creation with minimal required fields
* **Configuration:** Simple form with essential information only (name, description, template)
* **Use case:** Most common project setup for general use and internal management

#### 2. Azure Boards

Advanced project type requiring additional configuration fields for Azure integration.

* **Purpose:** Projects that need Azure Boards connectivity
* **Configuration:** Extended form with additional connection and authentication fields
* **Use case:** Teams that need to integrate with Azure DevOps Boards services

### Viewing Your Projects

Click on "View" under the Projects section to access your project management area:

![Projects List](/assets/projects-list-Du_Y4VpI.png)

The projects page shows:

* **All existing projects** with their templates and descriptions
* **New project** button to create additional projects
* Project details including:
  + Complete template with all standard fields for backlog creation
  + Last update dates
  + Template descriptions

### Creating a New Project

Click "New project" to create a new project with external tool integration:

![New Project Form](/assets/new-project-Dr8XEEUS.png)

Fill out the project details:

**Project type**: Choose the correct project type from the dropdown

* **Default**: Shows basic configuration fields for simple project setup
* **Azure Boards**: Displays additional connection and authentication fields for Azure integration

**Basic configuration** (available for all types):

* **Template type**: Select "Wynxx Template" (currently coming soon)
* **Name**: Enter your project name
* **Description**: Add a detailed project description

**Additional fields** (Azure Boards only):

* Connection details and authentication settings will appear for Azure Boards projects

Click "Save Project" to create your new project.

## Smart P.O. Assistant

> **Boost your refinement with Artificial Intelligence!**

For **Azure** and **Jira** projects, we've integrated an intelligent agent based on next-generation Language Models (**LLM**). This assistant acts as an expert co-pilot for all your **backlog items**.

### What can it do for you?

* **Title Optimization:** Creates clear, concise, and value-oriented titles for any item type.
* **Description Enrichment:** Writes the necessary context, including user story format *"As a [role], I want [action], for [benefit]"* when appropriate.
* **Acceptance Criteria:** Generates or improves the Acceptance Criteria list for all item types to ensure nothing is left to chance.

### Live Interaction Flow

1. **Proposal:** The agent analyzes your backlog item and offers you an improved version.
2. **Online Interaction:** You can chat with the assistant in real time to adjust specific details or request additional changes.
3. **Total Control:** You decide. You have the freedom to **Approve** the changes to update the item or **Reject** the suggestion if you prefer to keep your original version.

**Result:** Higher-quality backlog items, ready to enter the Sprint and providing real value to your development team.

### Multi-Backlog Overview

Once you have projects set up, you can manage multiple backlogs from a unified view:

![Multi-Backlog Overview](/assets/multi-backlog-Cof96KK7.png)

The multi-backlog interface provides:

* **Project cards** showing each backlog with descriptions
* **Last update information** to track recent activity
* **AI Backlog Generate** button for automated backlog creation
* Individual project management options

Example projects:

* **ToDo APP**: Create an todo app to manager my tasks
* **ChatBot AI**: Create a chatbot that uses GenAI to execute activities

## Managing Personas

### Viewing Personas

Access the Personas management system to create and manage user personas:

![Personas List](/assets/personas-list-BYwHflmh.png)

The personas page displays:

* **Name and Origin** columns showing persona details
* **System personas**: Pre-built personas (Product Owner, Scrum Master, Developer)
* **Custom personas**: User-created personas (Data Analyst, General Consumer)
* **Add Persona** button to create new personas

### Adding a New Persona

Click "Add Persona" to create a custom persona:

![Add Persona Form](/assets/add-persona-ByM38L9K.png)

Define your persona with:

**Name**: Identify the persona name

**Description**: Explain behavior and scope - what this persona represents and their role

**Style**: Define tone, language, and examples of voice - how this persona communicates

Click "Save" to create the persona or "Cancel" to discard changes.

## Best Practices

**Project Management:**

* Use descriptive project names and detailed descriptions
* Keep projects updated with recent activity
* Leverage the multi-backlog view for project overview

**Persona Creation:**

* Create personas that represent real user types
* Include detailed behavioral descriptions
* Define clear communication styles for each persona
* Mix system and custom personas based on your needs

**Workflow Organization:**

* Start with the Projects section to set up your development environment
* Use Personas to define user types for story creation
* Regularly review and update both projects and personas

## Integration Features

Story Creator 2.0 supports integration with external tools through the project configuration:

* Template-based project creation
* External tool synchronization
* Automated backlog generation with AI assistance

Last modified on February 26, 2026

[Story Creator 1.0 [Legacy]](/guides/features/story-creator)[Code Documenter](/feature-benchmark/code-documenter)

On this page

* [Overview](/guides/features/story-creator2#overview)
* [Getting Started](/guides/features/story-creator2#getting-started)
  + [Accessing Story Creator](/guides/features/story-creator2#accessing-story-creator)
* [Managing Projects](/guides/features/story-creator2#managing-projects)
  + [Available Project Types](/guides/features/story-creator2#available-project-types)
  + [Viewing Your Projects](/guides/features/story-creator2#viewing-your-projects)
  + [Creating a New Project](/guides/features/story-creator2#creating-a-new-project)
* [Smart P.O. Assistant](/guides/features/story-creator2#smart-po-assistant)
  + [What can it do for you?](/guides/features/story-creator2#what-can-it-do-for-you)
  + [Live Interaction Flow](/guides/features/story-creator2#live-interaction-flow)
  + [Multi-Backlog Overview](/guides/features/story-creator2#multi-backlog-overview)
* [Managing Personas](/guides/features/story-creator2#managing-personas)
  + [Viewing Personas](/guides/features/story-creator2#viewing-personas)
  + [Adding a New Persona](/guides/features/story-creator2#adding-a-new-persona)
* [Best Practices](/guides/features/story-creator2#best-practices)
* [Integration Features](/guides/features/story-creator2#integration-features)

---


# ORIGEM: https://docs.wynxx.app/api-guides/code-reviewer
API (How-To)

# Code Reviewer (API)

Examples of requests/responses for the Code Reviewer feature via API.

## Overview

Code Reviewer analyzes changes in Pull Requests and generates a detailed AI-based review. Depending on your VCS configuration, the result is published directly on the PR in the chosen platform (GitHub, GitLab, Azure DevOps), including description, summary, and recommendations.

* Authenticate and obtain an `access_token` (see [Authentication](/api-guides/authentication)).
* Submit a job to `POST /ai/review` with PR and model parameters.
* Poll `GET /ai/jobs/{jobId}/status` until `Completed`.
* The system may post directly to the PR and/or return artifacts (URIs) in the job status.

## Authentication

* Header: `Authorization: Bearer <access_token>`
* How to get the token: see [Authentication](/api-guides/authentication).

## Endpoint

* URL: `https://api.${INSTANCE}/ai/review`
* Method: `POST`
* Content-Type: `multipart/form-data` or `application/x-www-form-urlencoded`

### Key fields (form-data)

* `RunName`: logical execution name (e.g., `CodeReview`).
* `jobName`: job identifier (e.g., `DemoCodeReviewerGitHubJA`).
* `PromptId`: review prompt/strategy (e.g., `CodeReviewer__CodeReviewer_V1_Conventions`).
* `Llm`: model/provider (e.g., `${{ vars.LLM }}` in GitHub Actions).
* `PullRequestId`: PR number/id to analyze.
* `RepoName`: target repository (e.g., `owner/repo`).
* `AdditionalInstructions`: extra instructions to the reviewer (e.g., response language).
* `Conventions`: list/CSV of expected sections/conventions (e.g., `Header,Description,Summary,Recomendations,VulnExplanation`).

Note: additional VCS fields may be required depending on your setup (VCS connection name, default branches, etc.).

Notes:

* Content-Type: The API accepts both `multipart/form-data` (used in curl below) and `application/x-www-form-urlencoded` (used in Postman examples).
* Parameter names are case-sensitive depending on deployment; prefer `jobName`, but some environments use `jobname`.
* `RepoName` format depends on your VCS provider. For GitHub use `owner/repo`; for Azure DevOps or GitLab you may use the project/repo or a short name (e.g., `solution1`).

## Before you start (Portal pre-check)

In the Wynxx portal, confirm the job configuration is correct:

* Go to Settings → Jobs → Code Reviewer → your job (e.g., Demo Code Reviewer).
* Verify variables (Prompt, default LLM, repo/VCS settings) are aligned with what you’ll send via API.
* If you expect the system to comment on a PR, ensure the VCS connection and permissions are valid.

## Postman prerequisites

For environment setup, authentication (token), and generic request patterns, see [Postman Quickstart](/api-guides/postman-quickstart). Below are only the Code Reviewer–specific details.

### Code Reviewer request (Postman)

* Method: POST
* URL: `{{server}}/ai/review`
* Authorization: Bearer Token → Token: `{{token}}`
* Headers: `Content-Type: application/x-www-form-urlencoded`
* Body (x-www-form-urlencoded):
  + `RunName`: `CodeReviewer`
  + `jobName` (or `jobname` depending on environment): e.g., `DemoCodeReviewer`
  + `PromptId`: `CodeReviewer__CodeReviewer_V1_Conventions`
  + `Llm`: an LLM id or alias
  + `PullRequestId`: the target PR number/id (e.g., `2184`)
  + `RepoName`: repo identifier (`owner/repo` or `solution1`)
  + `Conventions`: comma-separated list, e.g., `Header,CheckLists,Description,Summary,Recomendations,VulnExplanation`
  + `AdditionalInstructions`: optional, e.g., `generate the answer in the following language en-US`

Tests tab snippet (if your API returns plain text id):

```
JavascriptCode



let idFromText = pm.response.text();
pm.environment.set("job-id", idFromText);
```

If it returns JSON, parse and use `id`/`jobId`/`job_id` as described in Troubleshooting.

### Check job status (Postman)

* Method: GET
* URL: `{{server}}/ai/jobs/{{job-id}}/status`
* Authorization: Bearer Token → Token: `{{token}}`

Statuses: `Pending`, `Running`, `Completed`, `Failed` (or `COMPLETED`/`FAILED`). For polling patterns and best practices, see [Postman Quickstart](/api-guides/postman-quickstart).

### List available LLMs (optional)

* Method: POST, URL: `{{server}}/bff/llm/list`
* Use generic request steps from [Postman Quickstart](/api-guides/postman-quickstart). Pick an id and set `Llm` in your review request.

### Verify PR output

Open the target Pull Request in your VCS (GitHub, GitLab, Azure DevOps) to confirm the generated review (description, summary, recommendations). Artifacts may also be returned in job status.

## Quick curl example (local)

```
TerminalCode



INSTANCE="your-domain"                     # e.g., dev.yourcompany.com
ACCESS_TOKEN="your-access-token"
REPO="owner/repo"
PR_NUMBER=123
LLM="gpt-4o-mini"                          # example
LANGUAGE="en-US"

curl --location "https://api.${INSTANCE}/ai/review" \
	--header "Authorization: Bearer ${ACCESS_TOKEN}" \
	--form 'RunName="CodeReview"' \
	--form 'jobName="DemoCodeReviewer"' \
	--form 'PromptId="CodeReviewer__CodeReviewer_V1_Conventions"' \
	--form "Llm=\"${LLM}\"" \
	--form "PullRequestId=\"${PR_NUMBER}\"" \
	--form "RepoName=\"${REPO}\"" \
		--form "AdditionalInstructions=\"generate the answer in the following language ${LANGUAGE}\"" \
	--form 'Conventions="Header,Description,Summary,Recomendations,VulnExplanation"'
```

## GitHub Actions example

Complete snippet to submit and monitor an AI-based PR review. Adjust values for your environment.

```
YAMLCode



- name: Prepare and Send for Code Review
				id: send_code_review
				run: |
					RESPONSE=$(curl --location 'https://api.${{ vars.INSTANCE }}/ai/review' \
						--header "Authorization: Bearer ${{ env.access_token }}" \
						--form 'RunName="CodeReview"' \
						--form 'jobName="DemoCodeReviewerGitHubJA"' \
						--form 'PromptId="CodeReviewer__CodeReviewer_V1_Conventions"' \
						--form 'Llm="${{ vars.LLM }}"' \
						--form 'PullRequestId="${{ github.event.pull_request.number }}"' \
						--form 'RepoName="julioarruda/vulnado"' \
						--form 'AdditionalInstructions="generate the answer in the following language ${{ vars.LANGUAGE }}"' \
						--form 'Conventions="Header,Description,Summary,Recomendations,VulnExplanation"')

					echo "API Response: $RESPONSE"

								# Resilient job id extraction (adjust to your payload)
					JOB_ID=$(echo "$RESPONSE" | jq -r '.id // .jobId // .job_id')
					if [ -z "$JOB_ID" ] || [ "$JOB_ID" = "null" ]; then
									echo "Failed to extract job id" >&2
						exit 1
					fi
					echo "job_id=$JOB_ID" >> $GITHUB_ENV

			- name: Monitor Code Review Job Status
				id: monitor_code_review_status
				run: |
					JOB_ID=${{ env.job_id }}
					STATUS="Pending"
					while [[ "$STATUS" != "Completed" ]]; do
						RESPONSE=$(curl --location "https://api.${{ vars.INSTANCE }}/ai/jobs/$JOB_ID/status" \
							--header "Authorization: Bearer ${{ env.access_token }}")
									STATUS=$(echo "$RESPONSE" | jq -r '.status // .state // "Pending"')
						echo "Current status: $STATUS"
						if [[ "$STATUS" == "Failed" || "$STATUS" == "Error" ]]; then
										echo "Job failed" >&2
							echo "$RESPONSE"
							exit 1
						fi
						sleep 10
					done

					echo "Final status: $STATUS"
					OUTPUT_URIS=$(echo "$RESPONSE" | jq -r '.results[]?.output[]?.uri // empty')
					echo "job_response=$RESPONSE" >> $GITHUB_ENV
					echo "output_uris=$OUTPUT_URIS" >> $GITHUB_ENV
```

> This process analyzes the Pull Request and generates a detailed AI-driven description directly on the PR in your chosen tool (GitHub, GitLab, Azure DevOps), according to your environment configuration.

## Best practices

* Set `PromptId`, `Conventions`, and `AdditionalInstructions` to guide tone and structure.
* Ensure the runner has permission to read the repo/PR and adequate token scopes.
* Use `jq` to inspect status JSON fields and confirm where logs, URIs, and messages live.

## Troubleshooting

* 401 Unauthorized: invalid/expired token; verify realm/client.
* 400 Bad Request: missing required fields (e.g., `PullRequestId`, `RepoName`).
* 404 Not Found (status): check the `jobId` returned on job creation.
* Job in `Failed/Error`: print the full status JSON to see error details and artifact links.
* Seeing a blank/empty job id response in Postman Tests: your API may return JSON rather than plain text. Parse and extract `id`/`jobId`/`job_id` keys.
* Status never leaves `Pending`: verify the configured job in the portal (Settings → Jobs → Code Reviewer) and confirm VCS permissions/token scopes.
* LLM not found: call `POST /bff/llm/list` and pick a valid id, then resubmit the review request.

## See also

* [Authentication](/api-guides/authentication)
* [Postman Quickstart](/api-guides/postman-quickstart)

Last modified on February 26, 2026

[Code Fixer (API)](/api-guides/code-fixer)[Postman Quickstart](/api-guides/postman-quickstart)

On this page

* [Overview](/api-guides/code-reviewer#overview)
* [Authentication](/api-guides/code-reviewer#authentication)
* [Endpoint](/api-guides/code-reviewer#endpoint)
  + [Key fields (form-data)](/api-guides/code-reviewer#key-fields-form-data)
* [Before you start (Portal pre-check)](/api-guides/code-reviewer#before-you-start-portal-pre-check)
* [Postman prerequisites](/api-guides/code-reviewer#postman-prerequisites)
  + [Code Reviewer request (Postman)](/api-guides/code-reviewer#code-reviewer-request-postman)
  + [Check job status (Postman)](/api-guides/code-reviewer#check-job-status-postman)
  + [List available LLMs (optional)](/api-guides/code-reviewer#list-available-llms-optional)
  + [Verify PR output](/api-guides/code-reviewer#verify-pr-output)
* [Quick curl example (local)](/api-guides/code-reviewer#quick-curl-example-local)
* [GitHub Actions example](/api-guides/code-reviewer#github-actions-example)
* [Best practices](/api-guides/code-reviewer#best-practices)
* [Troubleshooting](/api-guides/code-reviewer#troubleshooting)
* [See also](/api-guides/code-reviewer#see-also)

---


# ORIGEM: https://docs.wynxx.app/guides/features/code-fixer
Features

# Code Fixer

You can now quickly evaluate the code in your repositories with Code Fixer from the Wynnx application.

## When to use it

Use Code Fixer when you want to:

* Quickly evaluate and analyze code in your repositories
* Get feedback on code quality and potential issues
* Review results after processing your codebase
* Copy API request details for further use or troubleshooting

## Step‑by‑step tutorial

### 1) First look

This is what you see when you open Code Fixer

![first look at Code Fixer](/assets/standard-Dw7FW7Jt.png)

### 2) How to use Code Fixer

Type in all the request fields process. The tool will return a feedback of the processes in the right side of the screen.

![Fill all required fields](/assets/fill-fields-DiAPZG-W.png)

### 3) Checking the results

After the process is done you can check your repository.

![Repository](/assets/results-BxH6Sz7z.png)

### 4) Observations

* The API Request Section allows you to copy the request details being sent. It allows you to copy the request of the transaction being sent, where you can include the token if required.
* Check Include token: Mark this only if you want to include the token in the copied request.

## Related features

* [Code Documenter](/guides/features/code-documenter)
* [Code Reviewer](/api-guides/code-reviewer)
* [Story Creator](/guides/features/story-creator)

Last modified on February 26, 2026

[Code Documenter](/guides/features/code-documenter)[Code Tester](/guides/features/code-tester)

On this page

* [When to use it](/guides/features/code-fixer#when-to-use-it)
* [Step‑by‑step tutorial](/guides/features/code-fixer#stepbystep-tutorial)
  + [1) First look](/guides/features/code-fixer#1-first-look)
  + [2) How to use Code Fixer](/guides/features/code-fixer#2-how-to-use-code-fixer)
  + [3) Checking the results](/guides/features/code-fixer#3-checking-the-results)
  + [4) Observations](/guides/features/code-fixer#4-observations)
* [Related features](/guides/features/code-fixer#related-features)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/troubleshooting
Wynxx Assist Extension

# Troubleshooting

Common issues and solutions for the Wynxx Assist Extension.

## Connection Issues

**Verify server URL and network connectivity**

* Check that your Wynxx platform URL is correctly configured in settings
* Ensure your network connection is stable
* Test the URL in a web browser to confirm accessibility

**Check API key validity and permissions**

* Verify your API key hasn't expired
* Confirm you have the necessary permissions on the Wynxx platform
* Try regenerating your API key if authentication fails

**Ensure firewall allows extension connections**

* Check corporate firewall settings
* Verify that outbound HTTPS connections are allowed
* Contact your IT administrator if needed

## Performance Issues

**Clear extension cache in settings**

* Open VS Code settings
* Search for "Wynxx" or "Gft-ai-impact-core"
* Clear any cached data or temporary files
* Restart VS Code after clearing cache

**Restart IDE if extension becomes unresponsive**

* Close VS Code completely
* Restart the application
* Check if the extension loads properly

**Check for conflicting extensions**

* Disable other AI-powered extensions temporarily
* Test if the issue persists with minimal extensions
* Re-enable extensions one by one to identify conflicts

## Feature Not Working

**Update to latest extension version**

* Check VS Code marketplace for updates
* Update the extension if a newer version is available
* Restart VS Code after updating

**Verify workspace permissions in Wynxx platform**

* Confirm your user account has access to the workspace
* Check that the workspace is properly configured in Wynxx
* Verify project-level permissions

**Check extension logs for error details**

* Open VS Code Developer Console (Help > Toggle Developer Tools)
* Look for Wynxx-related error messages
* Check the Output panel for extension logs

## Authentication Problems

**Regenerate API key in Wynxx platform**

* Log into your Wynxx platform
* Navigate to API settings or user profile
* Generate a new API key
* Update the extension configuration with the new key

**Verify workspace access permissions**

* Confirm you have access to the specific workspace
* Check that your user role includes necessary permissions
* Contact your Wynxx administrator if needed

**Clear and reconfigure authentication settings**

* Remove existing authentication configuration
* Clear VS Code settings related to Wynxx
* Reconfigure from scratch with valid credentials

## Auto Ingestion Issues

**Files not being processed**

* Check that `.wynxx` configuration file exists in workspace root
* Verify Include Patterns settings cover your file types
* Review Extra Ignore patterns to ensure files aren't excluded

**Performance impact from ingestion**

* Reduce Batch Size if processing is too aggressive
* Increase Debounce Ms to reduce frequent processing
* Adjust Max File Size KB to exclude large files

**Status not updating**

* Enable "Show Status" in settings
* Enable "Verbose" logging for detailed information
* Check VS Code status bar for ingestion progress

## Legacy Transformer Issues

**Unsupported file types**

* Verify file extension is in supported languages (.cob, .cbl, .jcl, .ts)
* Check that Legacy Transformer is properly configured
* Update supported languages list if needed

**Business rules not detected**

* Ensure target file contains actual business logic
* Add context files to improve rule extraction
* Try different LLM models for better results

## Project Scaffolding Problems

**Cannot initialize .wynxx scaffold**

* Verify you have write permissions in the workspace
* Check that the workspace root is properly set
* Ensure no existing .wynxx file conflicts exist

**Configuration not persisting**

* Check file permissions on .wynxx directory
* Verify VS Code has access to write configuration files
* Try manual configuration if automated setup fails

## Getting Additional Help

If issues persist after trying these solutions:

1. **Check Extension Logs**

   * Open VS Code Output panel
   * Select "Wynxx" from the dropdown
   * Look for specific error messages
2. **Collect Diagnostic Information**

   * Extension version number
   * VS Code version
   * Operating system details
   * Error messages or logs
3. **Community Resources**

   * Check for known issues in release notes
   * Search for similar problems in documentation
   * Contact your Wynxx administrator for platform-specific issues

   ## Next Steps

   * [API Integration Guide](/api-guides)

Last modified on February 26, 2026

[MCP Server](/wynxx-assist-extension/mcp-server)[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)

On this page

* [Connection Issues](/wynxx-assist-extension/troubleshooting#connection-issues)
* [Performance Issues](/wynxx-assist-extension/troubleshooting#performance-issues)
* [Feature Not Working](/wynxx-assist-extension/troubleshooting#feature-not-working)
* [Authentication Problems](/wynxx-assist-extension/troubleshooting#authentication-problems)
* [Auto Ingestion Issues](/wynxx-assist-extension/troubleshooting#auto-ingestion-issues)
* [Legacy Transformer Issues](/wynxx-assist-extension/troubleshooting#legacy-transformer-issues)
* [Project Scaffolding Problems](/wynxx-assist-extension/troubleshooting#project-scaffolding-problems)
* [Getting Additional Help](/wynxx-assist-extension/troubleshooting#getting-additional-help)
* [Next Steps](/wynxx-assist-extension/troubleshooting#next-steps)

---


# ORIGEM: https://docs.wynxx.app/integrations/llms/google-gemini
LLMs

# Google Gemini (Vertex AI)

This guide explains how to use Google’s Gemini models via Vertex AI: create a project, enable APIs, generate a Service Account JSON key for authentication, set the region, and identify model names.

## Prerequisites

* Google Cloud project (you can create one in [https://console.cloud.google.com](https://console.cloud.google.com))
* Billing enabled for the project
* Permissions to manage IAM and Vertex AI (e.g., Project Owner or equivalent least‑privilege roles)

## Step 1 — Enable Vertex AI API

1. Open Google Cloud Console → APIs & Services → Library
2. Search for “Vertex AI API” and click Enable
3. Optional: also enable “Cloud Resource Manager API” and “IAM Service Account Credentials API” if you’ll automate IAM steps

## Step 2 — Create a Service Account and JSON key

1. Console: IAM & Admin → Service Accounts → Create Service Account
   * Name: `wynxx-vertex-bot` (example)
   * Grant this service account access to project:
     + Role: Vertex AI User (roles/aiplatform.user)
     + Optionally, Storage Object Viewer if loading data from GCS
2. Create the account, then go to Keys → Add Key → Create new key → JSON
3. Download the JSON file securely. This is your credential JSON for integration.

CLI alternative:

```
TerminalCode



PROJECT_ID="<your-project-id>"
SA_NAME="wynxx-vertex-bot"
SA_EMAIL="$SA_NAME@$PROJECT_ID.iam.gserviceaccount.com"

gcloud iam service-accounts create "$SA_NAME" \
  --project "$PROJECT_ID" \
  --display-name "Wynxx Vertex SA"

gcloud projects add-iam-policy-binding "$PROJECT_ID" \
  --member "serviceAccount:$SA_EMAIL" \
  --role roles/aiplatform.user

# Create JSON key
gcloud iam service-accounts keys create ./wynxx-vertex-key.json \
  --iam-account "$SA_EMAIL" \
  --project "$PROJECT_ID"
```

Security tip: Treat the JSON key like a password. Prefer Workload Identity Federation or short‑lived credentials in production.

## Step 3 — Choose Region and Model

Gemini availability varies by region. Common regions include `us-central1` and `europe-west4`.

* Region (Vertex AI Location): e.g., `us-central1`
* Models (examples; check current names):
  + `gemini-1.5-pro` (latest: often suffixed, e.g., `gemini-1.5-pro-002`)
  + `gemini-1.5-flash` (e.g., `gemini-1.5-flash-002`)

You can list models with gcloud:

```
TerminalCode



gcloud ai models list \
  --project "$PROJECT_ID" \
  --region "us-central1" \
  --format "table(name, displayName, versionId)"
```

For Vertex AI generative endpoints, the fully qualified name usually uses the publisher path, e.g., `publishers/google/models/gemini-1.5-pro`.

## Step 4 — Configure credentials for Wynxx

Provide the following to Wynxx (environment variables or your secret manager):

* GOOGLE\_APPLICATION\_CREDENTIALS: absolute path to the downloaded JSON key
* GCP\_PROJECT\_ID: your project ID
* GCP\_LOCATION: Vertex AI region (e.g., `us-central1`)
* GEMINI\_MODEL: model name (e.g., `gemini-1.5-pro`)

Example local setup:

```
TerminalCode



export GOOGLE_APPLICATION_CREDENTIALS="$HOME/secrets/wynxx-vertex-key.json"
export GCP_PROJECT_ID="<your-project-id>"
export GCP_LOCATION="us-central1"
export GEMINI_MODEL="gemini-1.5-pro"
```

Alternatively, activate the service account as ADC:

```
TerminalCode



gcloud auth application-default activate-service-account \
  --key-file="$GOOGLE_APPLICATION_CREDENTIALS"
```

## Quick verification (optional)

Test a call using your ADC token to Vertex AI’s GenerateContent endpoint:

```
TerminalCode



ACCESS_TOKEN=$(gcloud auth application-default print-access-token)
PROJECT_ID="$GCP_PROJECT_ID"
LOCATION="$GCP_LOCATION"
MODEL="publishers/google/models/$GEMINI_MODEL"

curl -s -X POST \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json" \
  "https://$LOCATION-aiplatform.googleapis.com/v1/projects/$PROJECT_ID/locations/$LOCATION/$MODEL:generateContent" \
  -d '{
        "contents": [
          {"role": "user", "parts": [{"text": "ping"}]}
        ]
      }'
```

If successful, the response contains `candidates[0].content` with the model output.

## Troubleshooting

* Permission denied: Ensure the service account has `roles/aiplatform.user` and the Vertex AI API is enabled.
* Invalid model/region: Confirm the model exists in your selected location and you used the correct publisher path.
* ADC issues: Verify `GOOGLE_APPLICATION_CREDENTIALS` points to a valid JSON key or that you activated ADC.
* Quotas: Ensure billing is enabled and you have sufficient quota for generative models.

## Best practices

* Prefer Workload Identity Federation or short‑lived credentials over long‑lived JSON keys in production.
* Store JSON keys securely (Secret Manager, Vault) and rotate regularly.
* Restrict IAM roles to least privilege.

Last modified on February 26, 2026

[Azure OpenAI](/integrations/llms/azure-openai)[LiteLLM Proxy](/integrations/llms/litellm)

On this page

* [Prerequisites](/integrations/llms/google-gemini#prerequisites)
* [Step 1 — Enable Vertex AI API](/integrations/llms/google-gemini#step-1--enable-vertex-ai-api)
* [Step 2 — Create a Service Account and JSON key](/integrations/llms/google-gemini#step-2--create-a-service-account-and-json-key)
* [Step 3 — Choose Region and Model](/integrations/llms/google-gemini#step-3--choose-region-and-model)
* [Step 4 — Configure credentials for Wynxx](/integrations/llms/google-gemini#step-4--configure-credentials-for-wynxx)
* [Quick verification (optional)](/integrations/llms/google-gemini#quick-verification-optional)
* [Troubleshooting](/integrations/llms/google-gemini#troubleshooting)
* [Best practices](/integrations/llms/google-gemini#best-practices)

---


# ORIGEM: https://docs.wynxx.app/installation/prerequisites
Installation

# Prerequisites

This comprehensive guide outlines all requirements for installing Wynxx across all supported environments. Use this as a definitive checklist before starting any installation.

## Overview

### Supported Environments

| Cloud Provider | Kubernetes Service | Status | Recommended |
| --- | --- | --- | --- |
| **Microsoft Azure** | AKS (Azure Kubernetes Service) | ✅ Full Support | Production |
| **Amazon Web Services** | EKS (Elastic Kubernetes Service) | ✅ Full Support | Production |
| **Google Cloud Platform** | GKE (Google Kubernetes Engine) | ✅ Full Support | Production |

### Resource Summary

| Resource | Minimum | Recommended | Notes |
| --- | --- | --- | --- |
| **Kubernetes Version** | 1.27 | 1.29+ | All clouds |
| **Nodes** | 3 | 5 | For high availability |
| **vCPUs per Node** | 4 | 8 | Total: 12-40 vCPUs |
| **RAM per Node** | 16 GB | 32 GB | Total: 48-160 GB |
| **Disk per Node** | 100 GB | 250 GB | SSD recommended |
| **Shared Storage (RWX)** | 100 GB | 500 GB | For configs, prompts, uploads |
| **Block Storage (RWO)** | 100 GB | 200 GB | For MongoDB |

---

## Common Requirements (All Clouds)

### Operator Workstation Requirements

| Software | Minimum Version | Purpose | Verification |
| --- | --- | --- | --- |
| **kubectl** | 1.24+ | Kubernetes CLI | `kubectl version --client` |
| **helm** | 3.12+ | Package manager | `helm version` |
| **Modern Browser** | Chrome/Firefox/Edge | Installer UI | Latest version |

### Wynxx-Provided Files

| File | Description | Provided By |
| --- | --- | --- |
| **Subscription.zip** | Tenant configuration package | Wynxx Team |
| **override-values.yaml** | Helm values customization | Generated by Installer |
| **ACR Credentials** | Container registry access | Wynxx Team |

### Subscription.zip Contents

The Subscription.zip must contain these directories:

```
Code



Subscription.zip
├── keycloak/
│   └── realm-export.json       # Keycloak realm configuration
├── keycloak-secret/
│   └── (secret files)          # Keycloak secrets
├── keycloak-themes/
│   └── (theme files)           # Custom themes
└── Subscription/
    └── prompts/                # AI prompts and configurations
```

---

## Cloud-Specific Prerequisites

For detailed prerequisites and cluster creation instructions for each cloud provider, see:

* [Azure (AKS) Prerequisites](/installation/cloud/azure) - Azure Kubernetes Service setup
* [AWS (EKS) Prerequisites](/installation/cloud/aws) - Elastic Kubernetes Service setup
* [GCP (GKE) Prerequisites](/installation/cloud/gcp) - Google Kubernetes Engine setup

---

## Kubernetes Components (All Clouds)

These components must be installed in every Kubernetes cluster:

### 1. NGINX Ingress Controller

```
TerminalCode



# Add Helm repository
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update

# Install
helm install ingress-nginx ingress-nginx/ingress-nginx \
  --namespace ingress-nginx \
  --create-namespace \
  --set controller.replicaCount=2

# Verify
kubectl get pods -n ingress-nginx
kubectl get svc -n ingress-nginx

# Get LoadBalancer IP/Hostname
kubectl get svc -n ingress-nginx ingress-nginx-controller -o jsonpath='{.status.loadBalancer.ingress[0]}'
```

### 2. Cert-Manager

```
TerminalCode



# Add Helm repository
helm repo add jetstack https://charts.jetstack.io
helm repo update

# Install
helm install cert-manager jetstack/cert-manager \
  --namespace cert-manager \
  --create-namespace \
  --set installCRDs=true

# Verify
kubectl get pods -n cert-manager
```

### 3. ClusterIssuer for Let's Encrypt

#### Option A: HTTP-01 Challenge (No wildcard support)

```
YAMLCode



apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: letsencrypt-prod
spec:
  acme:
    server: https://acme-v02.api.letsencrypt.org/directory
    email: your-email@example.com
    privateKeySecretRef:
      name: letsencrypt-prod-key
    solvers:
    - http01:
        ingress:
          class: nginx
```

#### Option B: DNS-01 Challenge with Cloudflare (Supports wildcards)

```
YAMLCode



apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: letsencrypt-prod-cloudflare
spec:
  acme:
    server: https://acme-v02.api.letsencrypt.org/directory
    email: your-email@example.com
    privateKeySecretRef:
      name: letsencrypt-prod-cloudflare-key
    solvers:
    - dns01:
        cloudflare:
          apiTokenSecretRef:
            name: cloudflare-api-token-secret
            key: api-token
```

Create Cloudflare secret:

```
TerminalCode



kubectl create secret generic cloudflare-api-token-secret \
  --namespace cert-manager \
  --from-literal=api-token=YOUR_CLOUDFLARE_API_TOKEN
```

### 4. Metrics Server (Optional but Recommended)

```
TerminalCode



kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml

# Verify
kubectl top nodes
kubectl top pods -A
```

---

## DNS & Certificate Requirements

### DNS Configuration

All the following DNS A records must point to the Ingress Controller LoadBalancer IP:

#### Core Endpoints (Required)

| DNS Record | Variable Name | Purpose | Example |
| --- | --- | --- | --- |
| **Frontend** | `frontendIngress` | Main web application (shell) | `wynxx.example.com` |
| **Backend API** | `backendIngress` | Main REST API | `api.wynxx.example.com` |
| **Backend Functional Tester** | `backendFtIngress` | Functional testing API | `apift.wynxx.example.com` |
| **Keycloak** | `keycloakIngress` | Authentication/SSO server | `auth.wynxx.example.com` |
| **API Route** | `apiRouteIngress` | AI routing (Architect AI, LLM, Agents) | `apiroute.wynxx.example.com` |
| **MFE (Microfrontends)** | `mfeIngress` | Microfrontend modules | `mfe.wynxx.example.com` |
| **As-Is Frontend** | `asIsIngress` | Legacy frontend support | `as-is.wynxx.example.com` |

#### Optional Endpoints

| DNS Record | Variable Name | Purpose | Example |
| --- | --- | --- | --- |
| **Redis Insight** | `redisIngress` | Redis management UI | `redis.wynxx.example.com` |
| **Zipkin** | `zipkinIngress` | Distributed tracing | `zipkin.wynxx.example.com` |

> 💡 **Tip**: If using DNS-01 challenge with wildcard certificates, you can use `*.wynxx.example.com` to cover all subdomains with a single certificate.

### override-values.yaml Endpoint Configuration

When configuring Wynxx, these endpoints are defined in the `global.endpoints` section:

```
YAMLCode



global:
  client: "YourCompany"
  cloud: azure  # azure, aws, gcp, others
  tenant: "wynxx-prod"
  networkAccessOption: ingress
  endpoints:
    frontendIngress: "wynxx.example.com"           # Main application shell
    backendIngress: "api.wynxx.example.com"        # Backend API
    backendFtIngress: "apift.wynxx.example.com"    # Functional Tester API
    keycloakIngress: "auth.wynxx.example.com"      # Authentication
    redisIngress: "redis.wynxx.example.com"        # Redis Insight (optional)
    zipkinIngress: "zipkin.wynxx.example.com"      # Tracing (optional)
    apiRouteIngress: "apiroute.wynxx.example.com"  # AI Routing (LLM, Arch, Agents)
    asIsIngress: "as-is.wynxx.example.com"         # Legacy frontend
  storage:
    storageClass: azurefile-csi              # RWX storage for shared files
    storageClassMongo: managed-csi           # RWO for MongoDB
    storageClassPostgress: managed-csi       # RWO for PostgreSQL
    storageClassKeycloak: managed-csi        # RWO for Keycloak
    storageName: ai-impact-storage           # Storage name prefix
  acr:
    username: "your-acr-username"            # Provided by Wynxx Team
    password: "your-acr-password"            # Provided by Wynxx Team

# Certificate configuration
certificate:
  enabled: true                    # Enable automatic TLS certificates
  issuer: letsencrypt              # ClusterIssuer name
  issuerType: ClusterIssuer        # Type of issuer
  tlsSecretName: tenant-certificate-secret  # Secret name for TLS
```

### Ingress Classes

| Cloud | Default Ingress Class |
| --- | --- |
| **Azure** | `nginx` |
| **AWS** | `nginx` |
| **GCP** | `nginx` |

### Certificate Types

| Challenge Type | Wildcard Support | DNS Provider Required | Complexity |
| --- | --- | --- | --- |
| **HTTP-01** | ❌ No | No | Simple |
| **DNS-01** | ✅ Yes | Yes (Cloudflare, Azure DNS, Route53, Cloud DNS) | Medium |

### Supported DNS Providers for DNS-01

| Cloud | Native DNS | Third-Party |
| --- | --- | --- |
| **Azure** | Azure DNS | Cloudflare |
| **AWS** | Route53 | Cloudflare |
| **GCP** | Cloud DNS | Cloudflare |

---

## Storage Requirements

### Storage Types Required

| Purpose | Access Mode | Minimum Size | Cloud Solution |
| --- | --- | --- | --- |
| **Shared Storage** | RWX (ReadWriteMany) | 100 GB | Azure Files, EFS, Filestore |
| **MongoDB** | RWO (ReadWriteOnce) | 100 GB | Managed Disk, EBS, PD-SSD |
| **PostgreSQL (Keycloak)** | RWO | 10 GB | Managed Disk, EBS, PD-SSD |
| **Redis** | RWO | 10 GB | Managed Disk, EBS, PD-SSD |
| **RabbitMQ** | RWO | 10 GB | Managed Disk, EBS, PD-SSD |

### Storage Class Summary

| Cloud | RWX StorageClass | RWO StorageClass |
| --- | --- | --- |
| **Azure** | `azurefile-csi-premium` | `managed-premium-retain` |
| **AWS** | `efs-sc` | `gp3` |
| **GCP** | `filestore-sc` | `pd-ssd` |

---

## Network & Endpoints

### Required Outbound Access

| Destination | Port | Purpose |
| --- | --- | --- |
| `gftai.azurecr.io` | 443 | Pull Wynxx container images |
| `acme-v02.api.letsencrypt.org` | 443 | Let's Encrypt certificates |
| `api.cloudflare.com` | 443 | Cloudflare DNS (if used) |
| Cloud APIs | 443 | Cloud provider APIs |

### Ingress Endpoints (Created by Wynxx)

| Ingress Name | Endpoint Variable | Purpose |
| --- | --- | --- |
| `ai-impact-ingress-backend` | `backendIngress` | Main backend API |
| `ai-impact-ingress-backend-functional-tester` | `backendFtIngress` | Functional testing |
| `ai-impact-ingress-frontend` | `asIsIngress` | Legacy frontend |
| `ai-impact-keycloak-ingress` | `keycloakIngress` | Authentication |
| `ai-impact-redis-ingress` | `redisIngress` | Redis Insight |
| `ai-impact-ingress-backend-routes` | `apiRouteIngress` | AI routing |
| `wynxx-mfe-host-ingress` | `frontendIngress` | Main app shell |
| `wynxx-*-mfe-ingress` | `mfeIngress` | Microfrontends |

### Microfrontend Modules

The Wynxx Stack includes these microfrontend modules served via `mfeIngress`:

| Module | Path | Description |
| --- | --- | --- |
| Code Documenter | `/code-documenter/remoteEntry.js` | Documentation generation |
| Code Fixer | `/code-fixer/remoteEntry.js` | Automatic code fixing |
| Code Tester | `/code-tester/remoteEntry.js` | Test generation |
| Code Dialoguer | `/code-dialoguer/remoteEntry.js` | Conversational coding |
| Story Creator | `/story-creator/remoteEntry.js` | User story generation |
| User Control | `/user-control/remoteEntry.js` | User management |
| Global Settings | `/global-settings/remoteEntry.js` | System settings |
| Cost Control | `/cost-control/remoteEntry.js` | Cost monitoring |
| Feature Benchmark | `/feature-benchmark/remoteEntry.js` | Performance benchmarks |

### Internal Services (Not Exposed)

| Service | Port | Purpose |
| --- | --- | --- |
| MongoDB | 27017 | Database |
| Redis | 6379 | Cache |
| RabbitMQ | 5672 | Message Queue |
| PostgreSQL | 5432 | Keycloak DB |

---

## Files Required from Wynxx Team

### Before Installation

| File | Description | How to Obtain |
| --- | --- | --- |
| **Subscription.zip** | Tenant configuration package | Contact Wynxx Team |
| **ACR Username** | Azure Container Registry username | Contact Wynxx Team |
| **ACR Password** | Azure Container Registry password | Contact Wynxx Team |
| **License Key** | Wynxx license (if applicable) | Contact Wynxx Team |

### Configuration Values Needed

| Value | Example | Description |
| --- | --- | --- |
| **Tenant Name** | `acme-corp` | Unique identifier for your installation |
| **Frontend URL** | `wynxx.example.com` | Public URL for web interface |
| **Backend URL** | `api.wynxx.example.com` | Public URL for API |
| **Admin Email** | `admin@example.com` | For Let's Encrypt and notifications |

---

## Verification Commands

Run these commands to verify your cluster is ready:

```
TerminalCode



echo "=== Cluster Info ==="
kubectl cluster-info

echo "=== Nodes ==="
kubectl get nodes

echo "=== Storage Classes ==="
kubectl get storageclass

echo "=== Ingress Controller ==="
kubectl get pods -n ingress-nginx
kubectl get svc -n ingress-nginx

echo "=== Cert-Manager ==="
kubectl get pods -n cert-manager

echo "=== ClusterIssuers ==="
kubectl get clusterissuers

echo "=== All Namespaces ==="
kubectl get namespaces
```

---

## Support

If you encounter issues during prerequisite setup:

1. Check the cloud-specific troubleshooting sections in the detailed guides
2. Verify all components are in `Running` or `Active` status
3. Check cloud provider quotas and limits
4. Contact Wynxx support with diagnostic output from verification commands

---

## Next Steps

* [Azure (AKS) Prerequisites](/installation/cloud/azure) - Complete Azure setup guide
* [AWS (EKS) Prerequisites](/installation/cloud/aws) - Complete AWS setup guide
* [GCP (GKE) Prerequisites](/installation/cloud/gcp) - Complete GCP setup guide
* [Step-by-step Installation](/installation/steps) - Helm installation guide
* [Post-installation Validation](/installation/validation) - Verify your installation

Last modified on February 26, 2026

[Overview](/installation)[Cloud-Specific Prerequisites](/installation/cloud)

On this page

* [Overview](/installation/prerequisites#overview)
  + [Supported Environments](/installation/prerequisites#supported-environments)
  + [Resource Summary](/installation/prerequisites#resource-summary)
* [Common Requirements (All Clouds)](/installation/prerequisites#common-requirements-all-clouds)
  + [Operator Workstation Requirements](/installation/prerequisites#operator-workstation-requirements)
  + [Wynxx-Provided Files](/installation/prerequisites#wynxx-provided-files)
  + [Subscription.zip Contents](/installation/prerequisites#subscriptionzip-contents)
* [Cloud-Specific Prerequisites](/installation/prerequisites#cloud-specific-prerequisites)
* [Kubernetes Components (All Clouds)](/installation/prerequisites#kubernetes-components-all-clouds)
  + [1. NGINX Ingress Controller](/installation/prerequisites#1-nginx-ingress-controller)
  + [2. Cert-Manager](/installation/prerequisites#2-cert-manager)
  + [3. ClusterIssuer for Let's Encrypt](/installation/prerequisites#3-clusterissuer-for-lets-encrypt)
  + [4. Metrics Server (Optional but Recommended)](/installation/prerequisites#4-metrics-server-optional-but-recommended)
* [DNS & Certificate Requirements](/installation/prerequisites#dns--certificate-requirements)
  + [DNS Configuration](/installation/prerequisites#dns-configuration)
  + [override-values.yaml Endpoint Configuration](/installation/prerequisites#override-valuesyaml-endpoint-configuration)
  + [Ingress Classes](/installation/prerequisites#ingress-classes)
  + [Certificate Types](/installation/prerequisites#certificate-types)
  + [Supported DNS Providers for DNS-01](/installation/prerequisites#supported-dns-providers-for-dns-01)
* [Storage Requirements](/installation/prerequisites#storage-requirements)
  + [Storage Types Required](/installation/prerequisites#storage-types-required)
  + [Storage Class Summary](/installation/prerequisites#storage-class-summary)
* [Network & Endpoints](/installation/prerequisites#network--endpoints)
  + [Required Outbound Access](/installation/prerequisites#required-outbound-access)
  + [Ingress Endpoints (Created by Wynxx)](/installation/prerequisites#ingress-endpoints-created-by-wynxx)
  + [Microfrontend Modules](/installation/prerequisites#microfrontend-modules)
  + [Internal Services (Not Exposed)](/installation/prerequisites#internal-services-not-exposed)
* [Files Required from Wynxx Team](/installation/prerequisites#files-required-from-wynxx-team)
  + [Before Installation](/installation/prerequisites#before-installation)
  + [Configuration Values Needed](/installation/prerequisites#configuration-values-needed)
* [Verification Commands](/installation/prerequisites#verification-commands)
* [Support](/installation/prerequisites#support)
* [Next Steps](/installation/prerequisites#next-steps)

---


# ORIGEM: https://docs.wynxx.app/installation/cloud
Installation

# Cloud-Specific Prerequisites

Choose your cloud to view detailed access, resources, and network requirements before installing Wynxx.

* [Azure prerequisites](/installation/cloud/azure)
* [AWS prerequisites](/installation/cloud/aws)
* [GCP prerequisites](/installation/cloud/gcp)

Last modified on February 26, 2026

[Prerequisites](/installation/prerequisites)[AWS EKS Prerequisites](/installation/cloud/aws)

---


# ORIGEM: https://docs.wynxx.app/integrations/llms/azure-openai
LLMs

# Azure OpenAI

This guide walks you through creating an Azure OpenAI resource, deploying a model, and retrieving the endpoint and key you’ll use in Wynxx.

## Prerequisites

* Azure subscription with access to Azure OpenAI Service
* Owner/Contributor permissions to create resources in the target subscription/resource group

## Step 1 — Create an Azure OpenAI resource

1. Sign in to the Azure portal: [https://portal.azure.com](https://portal.azure.com)
2. Search for “Azure OpenAI” and select the service.
3. Click Create and fill in:
   * Subscription: select your subscription
   * Resource group: choose existing or create new
   * Region: pick a region that supports your target model (see Microsoft’s model availability table)
   * Name: a unique name for the resource
   * Pricing tier: Standard S0
4. Click Review + Create → Create.

## Step 2 — Get endpoint and keys

1. After deployment, open your Azure OpenAI resource.
2. In the left menu, select Resource Management → Keys and Endpoint.
3. Copy:
   * Endpoint (for example: [https://your-openai-resource.openai.azure.com/](https://your-openai-resource.openai.azure.com/))
   * KEY 1 (or KEY 2)

You’ll use these values in Wynxx.

## Step 3 — Deploy a model

1. Open Azure OpenAI Studio: [https://oai.azure.com](https://oai.azure.com)
2. Select your subscription and the Azure OpenAI resource → Use resource.
3. Go to Management → Deployments.
4. Click Create new deployment and configure:
   * Deployment name: choose a memorable name (you’ll reference this in API calls)
   * Model: select a supported model (for example: gpt-4o or gpt-4o-mini)
5. Click Create.

Note: In Azure OpenAI, your API calls reference the Deployment name, not the raw model name.

## Values to configure in Wynxx

* AZURE\_OPENAI\_ENDPOINT: your resource Endpoint URL
* AZURE\_OPENAI\_API\_KEY: your KEY 1 (or KEY 2)
* AZURE\_OPENAI\_DEPLOYMENT: the model Deployment name you created
* Optional region-specific settings, if applicable to your environment

## Quick verification (optional)

You can validate the endpoint and key with a simple curl request (replace placeholders):

```
TerminalCode



curl -s -H "api-key: <AZURE_OPENAI_API_KEY>" \
     -H "Content-Type: application/json" \
     https://<YOUR_RESOURCE_NAME>.openai.azure.com/openai/deployments/<AZURE_OPENAI_DEPLOYMENT>/chat/completions?api-version=2024-06-01 \
     -d '{"messages":[{"role":"user","content":"ping"}]}'
```

A successful response returns JSON with a choices array.

## Tips and troubleshooting

* Access denied: Ensure your subscription is approved for Azure OpenAI and the user has RBAC permissions.
* Model not available: Choose a region that supports your model.
* Rate limits: Adjust Tokens per minute in the deployment as needed for your workload.
* Security: Store keys in a secret manager; rotate them regularly. Never commit keys to source control.

Last modified on February 26, 2026

[AWS Bedrock](/integrations/llms/aws-bedrock)[Google Gemini (Vertex AI)](/integrations/llms/google-gemini)

On this page

* [Prerequisites](/integrations/llms/azure-openai#prerequisites)
* [Step 1 — Create an Azure OpenAI resource](/integrations/llms/azure-openai#step-1--create-an-azure-openai-resource)
* [Step 2 — Get endpoint and keys](/integrations/llms/azure-openai#step-2--get-endpoint-and-keys)
* [Step 3 — Deploy a model](/integrations/llms/azure-openai#step-3--deploy-a-model)
* [Values to configure in Wynxx](/integrations/llms/azure-openai#values-to-configure-in-wynxx)
* [Quick verification (optional)](/integrations/llms/azure-openai#quick-verification-optional)
* [Tips and troubleshooting](/integrations/llms/azure-openai#tips-and-troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/guides/features/code-dialoguer
Features

# Code Dialoguer

The Code Dialoguer is an interactive chat interface for working with your code: ask questions, get explanations, and generate or refine content with AI.

This page mirrors the in‑app tutorial and follows the same flow shown in the UI.

## When to use it

* Ask “how/why” questions about your codebase
* Get quick examples, refactor suggestions, and explanations
* Inspect files generated by the tool and iterate on them
* Upload reference files to improve answer quality

## Step‑by‑step tutorial

### 1) Create a project

Begin by creating a new project. Choose a meaningful name so you can find it later.

![Create project screen](/assets/project-BJ-AKVcp.png)

### 2) New Dialogue

Click New Dialogue to start a fresh interaction. A blank screen opens for the new topic.

![New dialogue screen ready for a new request](/assets/new-dialogue-C0UEFX0C.png)

### 3) Start a dialogue and ask your request

Type in the request field what you want the tool to generate or process. The tool will return a dialogue containing the requested answer.

![Request input field with instructions](/assets/instruction-Fop67eiZ.png)

### 4) View Files

Click View Files to review files that were generated by the tool or are associated with your project.

![View files panel with generated files](/assets/files-fE75-L0F.png)

### 5) Ingestion Files

Choose Ingestion Files to upload additional documents (code, specs, datasets). After uploading, you can ask the tool to generate or analyze content based on those files for more complete answers.

![Ingestion files screen to add reference files](/assets/add-files-CjwSYcjS.png)

### 6) Top‑right menu options

In the upper‑right area you’ll find:

* Select Projects: pick one of the previously created projects
* Create a new one: start a new dialogue
* Tutorial: open this tutorial
* Delete Project: delete the current project (cannot be undone)

## Tips

* Be specific and include relevant code/context for better answers
* Use separate dialogues for distinct topics to keep context focused
* Upload files via Ingestion Files to enrich responses

## Related features

* [Code Documenter](/guides/features/code-documenter)
* [Code Reviewer](/api-guides/code-reviewer)
* [Story Creator](/guides/features/story-creator)

Last modified on February 26, 2026

[GitLab Integration](/integrations/vcs/gitlab)[Code Documenter](/guides/features/code-documenter)

On this page

* [When to use it](/guides/features/code-dialoguer#when-to-use-it)
* [Step‑by‑step tutorial](/guides/features/code-dialoguer#stepbystep-tutorial)
  + [1) Create a project](/guides/features/code-dialoguer#1-create-a-project)
  + [2) New Dialogue](/guides/features/code-dialoguer#2-new-dialogue)
  + [3) Start a dialogue and ask your request](/guides/features/code-dialoguer#3-start-a-dialogue-and-ask-your-request)
  + [4) View Files](/guides/features/code-dialoguer#4-view-files)
  + [5) Ingestion Files](/guides/features/code-dialoguer#5-ingestion-files)
  + [6) Top‑right menu options](/guides/features/code-dialoguer#6-topright-menu-options)
* [Tips](/guides/features/code-dialoguer#tips)
* [Related features](/guides/features/code-dialoguer#related-features)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/wynxx-assist
Wynxx Assist Extension

# About Wynxx Assist Extension

This page explains how to install and use the Wynxx Assist Extension for enhanced development productivity. Follow the steps below to set up and configure the extension.

## Overview

The Wynxx Assist Extension integrates directly with your development environment to provide AI-powered code assistance, documentation generation, and automated testing capabilities.

### Key Features

* **[ArchitectAI](/wynxx-assist-extension/Wynxx-Assist-Architect)** - Software architecture analysis and recommendations
* **[Code Documenter](/wynxx-assist-extension/Wynxx-Assist-Documentation)** - Automatic documentation generation
* **[Code Tester](/wynxx-assist-extension/Wynxx-Assist-Test)** - Unit test generation and optimization
* **[Code Dialoguer](/wynxx-assist-extension/Wynxx-Assist-Dialoguer)** - Interactive AI chatbot for code-related conversations
* **[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)** - Convert legacy code to modern programming languages
* **[MCP Server](/wynxx-assist-extension/mcp-server)** - Model Context Protocol integration for AI assistants

### Prerequisites

* VS Code or compatible IDE
* Active Wynxx platform access
* Valid API credentials

## Quick Navigation

* [📥 Installation](/wynxx-assist-extension/installation) - Step-by-step installation guide
* [⚙️ Configuration](/wynxx-assist-extension/configuration) - Detailed configuration settings
* [Troubleshooting](/wynxx-assist-extension/troubleshooting) - Common issues and solutions
* [🔌 MCP Server](/wynxx-assist-extension/mcp-server) - Model Context Protocol for AI assistants

## Getting Started

1. **Install the Extension** - Follow the [Installation Guide](/wynxx-assist-extension/installation)
2. **Configure Settings** - Set up your [Configuration](/wynxx-assist-extension/configuration)
3. **Get Support** - Check [Troubleshooting](/wynxx-assist-extension/troubleshooting) if needed

## Support

For additional help:

* [Wynxx Platform Documentation](/installation/prerequisites)
* [API Integration Guide](/api-guides)

Last modified on February 26, 2026

[Postman Quickstart](/api-guides/postman-quickstart)[About Wynxx Assist Configuration](/wynxx-assist-extension/configuration)

On this page

* [Overview](/wynxx-assist-extension/wynxx-assist#overview)
  + [Key Features](/wynxx-assist-extension/wynxx-assist#key-features)
  + [Prerequisites](/wynxx-assist-extension/wynxx-assist#prerequisites)
* [Quick Navigation](/wynxx-assist-extension/wynxx-assist#quick-navigation)
* [Getting Started](/wynxx-assist-extension/wynxx-assist#getting-started)
* [Support](/wynxx-assist-extension/wynxx-assist#support)

---


# ORIGEM: https://docs.wynxx.app/integrations/vcs/github
Vcs

# GitHub Integration

This page explains, step by step, how to create a GitHub Personal Access Token (PAT) for repository operations used by Wynxx. The instructions assume minimal prior knowledge of GitHub and focus only on repositories (branches, commits, and pull requests).

## What you will create

You need one token that allows Wynxx to:

* Create branches and push commits
* Open and update pull requests

Recommended: Create a Fine-grained personal access token limited to only the repositories Wynxx should manage, with only the minimum required permissions.

## Create a Fine-grained PAT (recommended)

1. Sign in to GitHub and open your profile menu (top-right) → Settings.
2. In the left sidebar, go to Developer settings → Personal access tokens → Fine-grained tokens.
3. Click Generate new token.
4. Token name: Enter something clear, for example: Wynxx Repo Token.
5. Expiration: Choose a short, reasonable duration (for example, 30 or 90 days). Plan to rotate regularly.
6. Resource owner: Select your user or the organization that owns the repositories Wynxx will access.
7. Repository access: Choose Only select repositories and pick the specific repository (or repositories) Wynxx should manage.
8. Permissions → Repository permissions: Set the following permissions only:
   * Contents: Read and write (required to push commits/create branches)
   * Pull requests: Read and write (required to open/update pull requests)
     Other permissions should remain at No access unless you have a specific need.
9. Click Generate token.
10. Copy the token and store it securely. You won’t be able to view it again.

Suggested environment variable name: WYNXX\_GITHUB\_REPO\_TOKEN

## Classic PAT (fallback option)

If Fine-grained tokens are not available for your account, you can use a Classic PAT. Note this is broader and less granular.

1. Settings → Developer settings → Personal access tokens → Tokens (classic).
2. Click Generate new token (classic).
3. Note: If your organization enforces SSO, you must authorize the token for the org after creation.
4. Token name and Expiration: Choose clear name and a short expiration.
5. Scopes: Check repo (this grants repo, repo\_deployment, public\_repo, repo, and security\_events). This is the minimum umbrella scope to allow git read/write and pull requests.
6. Generate token and copy it securely.

## Where to use this token in Wynxx

Provide this token wherever Wynxx needs to interact with GitHub repositories (for example, when creating branches, committing changes, and opening pull requests). If your deployment uses environment variables or a secret manager, save it under WYNXX\_GITHUB\_REPO\_TOKEN or your platform’s preferred name.

## Optional: Quick verification

You can verify basic read access with a simple API call:

```
TerminalCode



curl -H "Authorization: Bearer __YOUR_TOKEN__" \
	  -H "Accept: application/vnd.github+json" \
	  https://api.github.com/repos/{owner}/{repo}
```

If you receive 200 OK and repository JSON, the token can read the repo. For writing (push/PR), ensure you granted Contents: Read and write and Pull requests: Read and write (or repo scope on Classic).

## Tips and troubleshooting

* 403 Resource not accessible by integration: The token likely isn’t authorized for the organization (SSO) or doesn’t include the repository you’re targeting. For Fine-grained tokens, ensure the repo is selected. For Classic tokens under SSO, authorize the token for the org.
* Permission denied when pushing: Confirm Contents is set to Read and write and that your GitHub user has write permissions on the repository. Branch protection rules may prevent direct pushes—create a branch and open a PR instead.
* Token expired or revoked: Generate a new token and update your secret storage.
* Principle of least privilege: Keep permissions minimal and limit to only the repositories Wynxx needs.
* Store securely: Never commit tokens to source control. Use your CI/CD or cloud secret manager, and rotate regularly.

Last modified on February 26, 2026

[Azure DevOps Integration](/integrations/vcs/azure-devops)[GitLab Integration](/integrations/vcs/gitlab)

On this page

* [What you will create](/integrations/vcs/github#what-you-will-create)
* [Create a Fine-grained PAT (recommended)](/integrations/vcs/github#create-a-fine-grained-pat-recommended)
* [Classic PAT (fallback option)](/integrations/vcs/github#classic-pat-fallback-option)
* [Where to use this token in Wynxx](/integrations/vcs/github#where-to-use-this-token-in-wynxx)
* [Optional: Quick verification](/integrations/vcs/github#optional-quick-verification)
* [Tips and troubleshooting](/integrations/vcs/github#tips-and-troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/integrations/vcs/gitlab
Vcs

# GitLab Integration

This page explains, step by step, how to create a GitLab token for repository operations used by Wynxx. The instructions assume minimal prior knowledge of GitLab and focus only on repositories (branches, commits, and merge requests).

## What you will create

You need one token that allows Wynxx to:

* Create branches and push commits
* Open and update merge requests (MRs)

Recommended: Create a Project Access Token scoped to only the specific project Wynxx should manage. If that’s not possible, use a Personal Access Token (PAT) with the minimum scopes.

## Recommended: Project Access Token (scoped to one project)

1. Open your target project in GitLab (GitLab.com or your self-managed instance, for example: [https://gitlab.example.com](https://gitlab.example.com)).
2. Go to Settings → Access Tokens (sometimes labeled “Project access tokens”).
3. Name: Enter something clear, for example: Wynxx Repo Token.
4. Expiration: Choose a short, reasonable duration (for example, 30 or 90 days). Plan to rotate regularly.
5. Role: Select Developer (recommended). Developer can push to non-protected branches and create merge requests. If your policies require it for specific actions, use Maintainer—but prefer the least privilege needed.
6. Scopes: Check these only:
   * write\_repository (required to push commits and create branches; includes read\_repository)
   * api (required to create and manage merge requests via the API)
     Leave all other scopes unchecked.
7. Click Create project access token.
8. Copy the token and store it securely. You won’t be able to view it again.

Suggested environment variable name: WYNXX\_GITLAB\_REPO\_TOKEN

## Fallback: Personal Access Token (user-level)

If you can’t use a Project Access Token, create a user Personal Access Token (PAT):

1. Click your avatar (top-right) → Edit profile (or Preferences), then go to Access Tokens.
2. Name: Wynxx Repo Token
3. Expiration: Choose a short, reasonable duration and plan to rotate it.
4. Scopes: Check only:
   * write\_repository
   * api
5. Click Create personal access token and copy it securely.

Note: A Personal Access Token carries your user’s permissions across all projects you can access. Prefer a Project Access Token when possible to minimize blast radius.

## Where to use this token in Wynxx

Provide this token wherever Wynxx needs to interact with GitLab repositories (for example, when creating branches, committing changes, and opening merge requests). If your deployment uses environment variables or a secret manager, save it under WYNXX\_GITLAB\_REPO\_TOKEN or your platform’s preferred name.

## Optional: Quick verification

You can verify the token with a simple API call (use your instance URL if self-managed):

```
TerminalCode



curl --header "PRIVATE-TOKEN: __YOUR_TOKEN__" \
     https://gitlab.com/api/v4/projects/<url-encoded_namespace%2Frepo>

Example for a project "my-org/my-repo": replace <url-encoded_namespace%2Frepo> with my-org%2Fmy-repo.
```

If you receive 200 OK and project JSON, the token can read the repo. For writing (push/branch) and MRs, ensure you granted write\_repository and api scopes.

## Tips and troubleshooting

* 403 Forbidden or insufficient permissions: Confirm the token type (Project vs Personal), role (Developer or higher), and that the scopes include api and write\_repository. For Project tokens, ensure you created it in the correct project.
* Protected branches: You may not be able to push directly to protected branches. Create a feature branch and open a merge request instead, or adjust branch protection for your workflow.
* Token expired or revoked: Generate a new token and update your secret storage.
* Self-managed GitLab: Replace the api base URL ([https://gitlab.com/api/v4](https://gitlab.com/api/v4)) with your instance URL, for example: [https://gitlab.example.com/api/v4](https://gitlab.example.com/api/v4).
* Least privilege: Keep scopes minimal and prefer Project Access Tokens limited to only the projects Wynxx needs.
* Store securely: Never commit tokens to source control. Use your CI/CD or cloud secret manager and rotate regularly.

Last modified on February 26, 2026

[GitHub Integration](/integrations/vcs/github)[Code Dialoguer](/guides/features/code-dialoguer)

On this page

* [What you will create](/integrations/vcs/gitlab#what-you-will-create)
* [Recommended: Project Access Token (scoped to one project)](/integrations/vcs/gitlab#recommended-project-access-token-scoped-to-one-project)
* [Fallback: Personal Access Token (user-level)](/integrations/vcs/gitlab#fallback-personal-access-token-user-level)
* [Where to use this token in Wynxx](/integrations/vcs/gitlab#where-to-use-this-token-in-wynxx)
* [Optional: Quick verification](/integrations/vcs/gitlab#optional-quick-verification)
* [Tips and troubleshooting](/integrations/vcs/gitlab#tips-and-troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/integrations/sast/Csv
Sast

# CSV FILE Integration

The application allows you to upload csv files through the Code Fixer module, to validate errors or vulnerabilities in your repository.

# Instructions

1. Go to the Wynnx page
2. Go to the Settings module
3. Add a new SAST type csv file
4. Download the Excel spreadsheet
5. Read the Excel spreadsheet with the vulnerabilities or inject necessary for your test
   7)Consume the codefixer api from postman or from the frontend option of the application
   Assistant to the plant
6. Validate that your result is successful

## Tips

* Remember that you must have a job created for codefix
* Remember to select the VCS default with your test repository

Last modified on February 26, 2026

[LiteLLM Proxy](/integrations/llms/litellm)[SonarCloud / SonarQube Integration](/integrations/sast/sonar)

On this page

* [Tips](/integrations/sast/Csv#tips)

---


# ORIGEM: https://docs.wynxx.app/api/settings-setup
Gft.Ai.Impact.Api.WebApi

# SettingsSetup

Endpoint:`__API_BASE_URL__`

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/config

### Responses

Success

No data returned

GET /bff/settings/config

```
curl --request GET \
  --url __API_BASE_URL__/bff/settings/config
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
No example
```

plain

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/setup

### Responses

Success

No data returned

GET /bff/settings/setup

```
curl --request GET \
  --url __API_BASE_URL__/bff/settings/setup
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
No example
```

plain

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/agile/type

### Responses

Success

No data returned

GET /bff/settings/agile/type

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/llm/model/type

### Responses

Success

No data returned

GET /bff/settings/llm/model/type

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/llm/type

### Responses

Success

No data returned

GET /bff/settings/llm/type

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/jobs/type

### Responses

Success

No data returned

GET /bff/settings/jobs/type

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/vcs/data

### Responses

Success

No data returned

GET /bff/settings/vcs/data

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/vcs/type

### Responses

Success

No data returned

GET /bff/settings/vcs/type

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/settings/setup/save

### Request Body

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

### Responses

Success

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

POST /bff/settings/setup/save

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/settings/setup/update

### Request Body

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

### Responses

Success

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

PUT /bff/settings/setup/update

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/settings/setup/delete

### Request Body

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

### Responses

Success

* `llms`object
* `jobs`object
* `vcs`object
* `sast`object
* `storyCreator`object

DELETE /bff/settings/setup/delete

---

[Settings](/api/settings)[Users](/api/users)

---


# ORIGEM: https://docs.wynxx.app/integrations/llms/litellm
LLMs

# LiteLLM Proxy

This guide explains how to use LiteLLM as a unified proxy to route LLM requests to multiple providers (OpenAI, Azure, Bedrock, Gemini, and others) from a single endpoint. LiteLLM is already deployed as part of the Wynxx cluster — you just need to access it, configure your providers and models, and point Wynxx to it.

## What is LiteLLM?

LiteLLM is an open-source proxy server that provides a **unified OpenAI-compatible API** in front of 100+ LLM providers. Instead of configuring each provider individually in every application, you configure them once in LiteLLM and consume a single endpoint.

Key benefits:

* **Single endpoint** for all providers — switch models without changing application config
* **OpenAI-compatible API** — any tool that speaks OpenAI format works out of the box
* **Centralized key management** — API keys live in LiteLLM, not scattered across apps
* **Load balancing and fallbacks** — route between models or providers automatically
* **Usage tracking** — monitor cost and token usage across all models in one place

## Prerequisites

* `kubectl` configured with access to the Wynxx cluster
* Credentials for the LLM provider(s) you want to add (e.g., Azure OpenAI key, AWS Bedrock credentials)
* The LiteLLM master key defined during installation (check with your cluster administrator)

## Step 1 — Access the LiteLLM Admin UI

LiteLLM is deployed inside the cluster without an external URL. To access it, use a Kubernetes port-forward:

```
TerminalCode



# Find the LiteLLM pod
kubectl get pods -n <namespace> | grep litellm

# Forward the LiteLLM port to your machine
kubectl port-forward -n <namespace> svc/litellm 4000:4000
```

Once running, open your browser at:

* **Admin UI**: [http://localhost:4000/ui](http://localhost:4000/ui)
* **API docs (Swagger)**: [http://localhost:4000](http://localhost:4000)

Log in with the LiteLLM master key configured during installation.

## Step 2 — Add a provider (Virtual Key)

LiteLLM uses **Virtual Keys** to manage credentials for each provider. To add a provider:

1. In the Admin UI, go to **Virtual Keys**
2. Click **Generate Key**
3. Fill in the details:
   * **Key alias**: a descriptive name (e.g., `azure-production`, `bedrock-us-east`)
   * Set any rate limits or budget constraints if needed
4. Click **Create**

Alternatively, via API:

```
TerminalCode



curl -s -X POST http://localhost:4000/key/generate \
  -H "Authorization: Bearer <LITELLM_MASTER_KEY>" \
  -H "Content-Type: application/json" \
  -d '{
    "key_alias": "azure-production",
    "max_budget": 100
  }'
```

Save the generated key — you will use it later to configure Wynxx.

## Step 3 — Add a model

Models in LiteLLM map a **model name** (what consumers request) to a **provider-specific deployment** (where the request actually goes).

1. In the Admin UI, go to **Models**
2. Click **Add Model**
3. Configure the mapping:

   * **Model Name** (public alias): the name consumers will use (e.g., `gpt-4o`, `claude-3`)
   * **Provider**: select the LLM provider (e.g., `azure`, `bedrock`, `vertex_ai`)
   * **LiteLLM Model**: the provider-specific model identifier
     + Azure: `azure/<your-deployment-name>`
     + AWS Bedrock: `bedrock/<model-id>` (e.g., `bedrock/anthropic.claude-3-5-sonnet-20241022-v2:0`)
     + Google Vertex: `vertex_ai/<model-name>`
     + OpenAI: `openai/<model-name>`
   * **API Base**: the provider endpoint URL (e.g., `https://your-resource.openai.azure.com/` for Azure)
   * **API Key**: the provider's API key or credentials
4. Click **Save**

Example via API — adding an Azure OpenAI model:

```
TerminalCode



curl -s -X POST http://localhost:4000/model/new \
  -H "Authorization: Bearer <LITELLM_MASTER_KEY>" \
  -H "Content-Type: application/json" \
  -d '{
    "model_name": "gpt-4o",
    "litellm_params": {
      "model": "azure/my-gpt4o-deployment",
      "api_base": "https://my-resource.openai.azure.com/",
      "api_key": "<AZURE_OPENAI_API_KEY>",
      "api_version": "2024-06-01"
    }
  }'
```

You can add multiple models and even multiple deployments for the same model name (LiteLLM will load-balance between them).

## Step 4 — Verify the model works

Test the model through LiteLLM's OpenAI-compatible endpoint:

```
TerminalCode



curl -s -X POST http://localhost:4000/v1/chat/completions \
  -H "Authorization: Bearer <LITELLM_VIRTUAL_KEY>" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gpt-4o",
    "messages": [{"role": "user", "content": "ping"}]
  }'
```

A successful response returns JSON with a `choices` array, just like calling OpenAI directly.

## Step 5 — Configure Wynxx to use LiteLLM

Wynxx recognizes LiteLLM through its **Azure OpenAI** provider type, since LiteLLM exposes an OpenAI-compatible API.

1. Open **Settings → LLM** in the Wynxx portal
2. Select provider: **Azure OpenAI**
3. Fill in:
   * **Endpoint / Base URL**: the LiteLLM service URL inside the cluster (e.g., `http://litellm:4000` or the internal Kubernetes service DNS)
   * **API Key**: the Virtual Key you generated in [Step 2](/integrations/llms/litellm#step-2--add-a-provider-virtual-key)
   * **Deployment / Model name**: the model name you configured in [Step 3](/integrations/llms/litellm#step-3--add-a-model) (e.g., `gpt-4o`)
4. Click **Test connection** and then **Save**

> For detailed instructions on the LLM settings screen, see [Application Settings — LLM](/settings/application#llm-settings).

## Tips and troubleshooting

* **Connection refused on port-forward**: Ensure the port-forward is still running. It drops if your terminal closes or the pod restarts.
* **401 Unauthorized**: Verify you are using the correct LiteLLM master key (for admin operations) or virtual key (for model calls).
* **Model not found**: Confirm the model name in your request matches exactly what you configured in LiteLLM (case-sensitive).
* **Provider errors (upstream 4xx/5xx)**: The issue is between LiteLLM and the provider — check credentials, endpoint URL, and that the model/deployment exists on the provider side.
* **Checking logs**: Use `kubectl logs -n <namespace> <litellm-pod>` to inspect request/response details.

## Best practices

* Configure **fallback models** in LiteLLM so requests automatically retry on a different provider if one fails.
* Use **virtual keys with budgets** to prevent unexpected cost spikes.
* Add the same model from multiple providers for **high availability**.
* Store provider API keys only in LiteLLM — Wynxx only needs the LiteLLM virtual key.

Last modified on February 26, 2026

[Google Gemini (Vertex AI)](/integrations/llms/google-gemini)[CSV FILE Integration](/integrations/sast/Csv)

On this page

* [What is LiteLLM?](/integrations/llms/litellm#what-is-litellm)
* [Prerequisites](/integrations/llms/litellm#prerequisites)
* [Step 1 — Access the LiteLLM Admin UI](/integrations/llms/litellm#step-1--access-the-litellm-admin-ui)
* [Step 2 — Add a provider (Virtual Key)](/integrations/llms/litellm#step-2--add-a-provider-virtual-key)
* [Step 3 — Add a model](/integrations/llms/litellm#step-3--add-a-model)
* [Step 4 — Verify the model works](/integrations/llms/litellm#step-4--verify-the-model-works)
* [Step 5 — Configure Wynxx to use LiteLLM](/integrations/llms/litellm#step-5--configure-wynxx-to-use-litellm)
* [Tips and troubleshooting](/integrations/llms/litellm#tips-and-troubleshooting)
* [Best practices](/integrations/llms/litellm#best-practices)

---


# ORIGEM: https://docs.wynxx.app/installation
Installation

# Overview

Get Wynxx up and running in your environment. This guide covers prerequisites, installation steps, and validation for all supported cloud platforms.

## Installation Steps

| Step | Description |
| --- | --- |
| [**Prerequisites**](/installation/prerequisites) | Kubernetes cluster, storage, DNS requirements and resource planning |
| [**Cloud Setup**](/installation/cloud) | Cloud-specific configurations for Azure, AWS, or GCP |
| [**Installation Steps**](/installation/steps) | Complete Wynxx deployment using Helm charts |
| [**Override Values**](/installation/override-values) | Customize deployment configuration and settings |
| [**Validation**](/installation/validation) | Verify installation and test core functionality |

## Supported Platforms

| Cloud Provider | Kubernetes Service | Status |
| --- | --- | --- |
| **Microsoft Azure** | AKS (Azure Kubernetes Service) | ✅ Fully Supported |
| **Amazon Web Services** | EKS (Elastic Kubernetes Service) | ✅ Fully Supported |
| **Google Cloud Platform** | GKE (Google Kubernetes Engine) | ✅ Fully Supported |

## Quick Start

1. **Prepare Environment**: Set up Kubernetes cluster with required resources
2. **Configure DNS**: Set up domain and SSL certificates
3. **Deploy Wynxx**: Use Helm charts for installation
4. **Initial Setup**: Run Setup Wizard for first-time configuration

## Tips

* Review prerequisites thoroughly before starting installation
* Ensure proper RBAC permissions for Kubernetes deployment
* Plan DNS and certificate strategy in advance
* Use the Setup Wizard for guided initial configuration

Last modified on February 26, 2026

[FAQ (Frequently Asked Questions)](/getting-started/faq)[Prerequisites](/installation/prerequisites)

On this page

* [Installation Steps](/installation#installation-steps)
* [Supported Platforms](/installation#supported-platforms)
* [Quick Start](/installation#quick-start)
* [Tips](/installation#tips)

---


# ORIGEM: https://docs.wynxx.app/api/users
Gft.Ai.Impact.Api.WebApi

# Users

Endpoint:`__API_BASE_URL__`

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/list

### Responses

Success

No data returned

GET /bff/user-control/users/list

```
curl --request GET \
  --url __API_BASE_URL__/bff/user-control/users/list
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
No example
```

plain

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/{id}

### path Parameters

* `id`string · required · style: simple

### Responses

Success

No data returned

GET /bff/user-control/users/{id}

```
curl --request GET \
  --url __API_BASE_URL__/bff/user-control/users/:id
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
No example
```

plain

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/create

### Request Body

* `Username`string
* `Email`string
* `Password`string
* `Enabled`boolean
* `GroupIds`string[]
* `Roles`string[]

### Responses

Success

No data returned

POST /bff/user-control/users/create

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/edit/{id}

### path Parameters

* `id`string · required · style: simple

### Request Body

* `Username`string
* `Email`string
* `Password`string
* `Enabled`boolean
* `GroupIds`string[]
* `Roles`string[]

### Responses

Success

No data returned

PUT /bff/user-control/users/edit/{id}

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/Deactivate/{id}

### path Parameters

* `id`string · required · style: simple

### Responses

Success

No data returned

PUT /bff/user-control/users/Deactivate/{id}

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/Activate/{id}

### path Parameters

* `id`string · required · style: simple

### Responses

Success

No data returned

PUT /bff/user-control/users/Activate/{id}

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/{id}/Status

### path Parameters

* `id`string · required · style: simple

### Request Body

* `Enabled`boolean

### Responses

Success

No data returned

PUT /bff/user-control/users/{id}/Status

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/delete/{id}

### path Parameters

* `id`string · required · style: simple

### Responses

Success

No data returned

DELETE /bff/user-control/users/delete/{id}

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/user-control/users/me/permissions

### Responses

Success

No data returned

GET /bff/user-control/users/me/permissions

---

[SettingsSetup](/api/settings-setup)

---


# ORIGEM: https://docs.wynxx.app/settings/wizard
Settings

# Wizard Settings

The Wizard Settings guide walks you through the initial configuration of Wynxx. On first access, you’ll complete a few simple sections to get the platform ready. Only the LLM configuration is required; you can edit all sections later in Settings.

## What you’ll configure

* LLM: the Large Language Model provider and defaults (required)
* VCS: your Version Control System connection (optional)
* SAST: static analysis connection (optional)
* Story Creator: defaults for story generation (optional)

> You can rerun the wizard or change any setting later under Settings.

## LLM

In this section you select and configure the Large Language Model (LLM) that Wynxx will use. Fields shown can vary depending on the provider you choose.

Common fields you may see:

* Provider/type (e.g., Azure OpenAI, OpenAI, Google, Bedrock)
* Model name or deployment (e.g., gpt‑4o‑mini, text‑embedding‑3‑large)
* API base URL / endpoint
* API key or credential reference
* Optional: temperature, max tokens, system prompt

Steps:

1. Pick the provider in the LLM list
2. Fill the required fields for that provider
3. Optionally tune defaults (temperature, max tokens) as needed
4. Save the LLM configuration

![LLM configuration screen](/assets/llm-keAYmJHK.png)

### Notes

* Different providers can surface additional fields; the UI will guide you based on your selection
* You can add or change providers later in Settings without rerunning the full wizard

## VCS

Connect Wynxx to your Version Control System to enable repository‑aware experiences.

Typical provider options include GitHub, Azure DevOps, or GitLab. Depending on the choice, you’ll provide:

* Organization/owner (and Project for Azure DevOps)
* Repository (name or URL)
* Personal Access Token (PAT) or app credentials with the right scopes
* Optional: base URL for self‑hosted/on‑prem instances

Steps:

1. Choose your VCS provider
2. Enter organization/owner and repository information
3. Provide a PAT/token with repository read/write as needed by your workflows
4. Save and test the connection

![VCS selection and configuration fields](/assets/vcs-B5am5Cq0.png)

### Tips

* Ensure the PAT has sufficient scopes (repo access, code read/write if required)
* For Azure DevOps, include the correct Organization and Project
* For self‑hosted GitLab/GitHub Enterprise, provide the server URL

## SAST

Connect your SAST provider to enrich reviews with static analysis results. Common options include SonarCloud and SonarQube.

You’ll typically provide:

* Server URL (for SonarQube)
* Organization (for SonarCloud)
* Project key or identifier
* Authentication token

Steps:

1. Choose your SAST provider (SonarCloud or SonarQube)
2. Enter the server/organization and project identifiers
3. Provide the token with appropriate permissions
4. Save and test the connection

![SAST configuration screen](/assets/sast-CEg_rXD3.png)

#### For SonarQube users

* Use the correct base URL of your SonarQube server
* Create a token in your SonarQube profile and keep it secure
* Ensure the token/user can access the target project

## Story Creator

Configure defaults for generating stories. This step is optional and can be refined later.

Typical options you may set:

* Target workspace/space or project in your planning tool
* Default labels/tags or priority conventions
* Optional mapping or templates used when generating story content

Steps:

1. Choose where stories should be created by default
2. Configure naming, labels, or other conventions as needed
3. Save your preferences

![Story Creator default settings](/assets/story-creator-DC0Hko9c.png)

## Finish and next steps

* Review your selections and click Finish
* You can revisit any section later in Settings without rerunning the entire wizard
* If you encounter permission or token errors, verify scopes and try the “Test connection” actions (where available)

## Troubleshooting

* Invalid credentials or insufficient scopes: regenerate the token with required permissions
* Network or self‑hosted endpoints: verify base URL, SSL, and firewall rules
* LLM provider errors: confirm model/deployment names and quota limits

Last modified on February 26, 2026

[User Control](/settings/user-control)[Overview](/integrations)

On this page

* [What you’ll configure](/settings/wizard#what-youll-configure)
* [LLM](/settings/wizard#llm)
  + [Notes](/settings/wizard#notes)
* [VCS](/settings/wizard#vcs)
  + [Tips](/settings/wizard#tips)
* [SAST](/settings/wizard#sast)
  + [For SonarQube users](/settings/wizard#for-sonarqube-users)
* [Story Creator](/settings/wizard#story-creator)
* [Finish and next steps](/settings/wizard#finish-and-next-steps)
* [Troubleshooting](/settings/wizard#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/integrations/vcs/azure-devops
Vcs

# Azure DevOps Integration

This page explains, step by step, how to create the two Personal Access Tokens (PATs) required by Wynxx to integrate with Azure DevOps. The instructions assume minimal prior knowledge of Azure DevOps and are written to be easy to follow.

## What you will create

You need two separate PATs, each with only the minimum required permissions:

1. Agile PAT (Work Items)

* Purpose: Create and edit backlog/work items used by the Agile features in Wynxx.
* Scope to select: Work Items (Read & write)

2. Git PAT (Code)

* Purpose: Read and write to Git repos to create branches, push commits, and open pull requests.
* Scope to select: Code (Read & write)

Security best practice: Keep scopes minimal, set the shortest practical expiration, limit the PAT to the correct organization, and store it securely (for example, as a secret in your CI/CD or secret manager).

## Before you start

* You must have an Azure DevOps account and access to your organization at [https://dev.azure.com/{your-organization}](https://dev.azure.com/%7Byour-organization%7D).
* Your organization must allow PAT creation. If you don’t see the Personal access tokens menu, ask an Azure DevOps administrator to enable PAT creation or to create the tokens for you.

## How to open the Personal Access Tokens page

1. Sign in to Azure DevOps: [https://dev.azure.com/{your-organization}](https://dev.azure.com/%7Byour-organization%7D)
2. In the top-right corner, select your avatar and then choose Personal access tokens.
3. Click + New Token.

Tip: If you have access to multiple organizations, make sure the Organization field matches the one where your projects and repos live.

## Create the Agile PAT (Work Items)

Follow these steps exactly to grant only the permissions needed for Wynxx Agile features:

1. Name: Enter a clear name, for example: Wynxx Agile PAT
2. Organization: Select your target organization.
3. Expiration: Choose the shortest practical duration (for example, 30 or 90 days). Plan to rotate regularly.
4. Scopes: Click Show all scopes, then check only:
   * Work Items (Read & write)
     Leave all other scopes unchecked.
5. Click Create.
6. Copy the token shown and store it in a secure location. You will not be able to view it again later.

Suggested variable name in your environment: WYNXX\_AZDO\_AGILE\_PAT

## Create the Git PAT (Code)

Follow these steps to enable Wynxx to create branches, push commits, and open pull requests:

1. Click + New Token again.
2. Name: Wynxx Git PAT
3. Organization: Select the same organization as above.
4. Expiration: Choose a short, reasonable duration and plan to rotate it.
5. Scopes: Click Show all scopes, then check only:
   * Code (Read & write)
     Leave all other scopes unchecked.
6. Click Create.
7. Copy the token and store it securely.

Suggested variable name in your environment: WYNXX\_AZDO\_GIT\_PAT

## Where to use these PATs in Wynxx

* Agile PAT (Work Items): Configure this token wherever Wynxx needs to create or update backlog items (for example, integrations or automation that open work items).
* Git PAT (Code): Configure this token wherever Wynxx needs to create branches, commit changes, and open pull requests in your Azure Repos Git repositories.

If your deployment uses environment variables or a secret manager, save each PAT under the suggested names above or according to your platform’s naming conventions.

## Tips and troubleshooting

* Can’t find the Personal access tokens menu? Your organization may restrict PAT creation. Contact your Azure DevOps administrator.
* Errors like “insufficient scope” or “forbidden”: Double-check you selected the exact scopes listed above and that the PAT hasn’t expired. If you changed scopes, you must create a new PAT; scopes can’t be edited after creation.
* Multiple organizations: Ensure the PAT’s Organization is the one where your projects and repositories exist.
* Protect your PATs: Treat PATs like passwords. Never commit them to source control. Store them as secrets in your CI/CD or secret manager. Rotate them regularly and immediately revoke any token you suspect is exposed.

## Summary of scopes to select

* Agile PAT: Work Items (Read & write)
* Git PAT: Code (Read & write)

For reference, see Microsoft’s documentation: Use personal access tokens (PATs) in Azure DevOps.

Last modified on February 26, 2026

[SonarCloud / SonarQube Integration](/integrations/sast/sonar)[GitHub Integration](/integrations/vcs/github)

On this page

* [What you will create](/integrations/vcs/azure-devops#what-you-will-create)
* [Before you start](/integrations/vcs/azure-devops#before-you-start)
* [How to open the Personal Access Tokens page](/integrations/vcs/azure-devops#how-to-open-the-personal-access-tokens-page)
* [Create the Agile PAT (Work Items)](/integrations/vcs/azure-devops#create-the-agile-pat-work-items)
* [Create the Git PAT (Code)](/integrations/vcs/azure-devops#create-the-git-pat-code)
* [Where to use these PATs in Wynxx](/integrations/vcs/azure-devops#where-to-use-these-pats-in-wynxx)
* [Tips and troubleshooting](/integrations/vcs/azure-devops#tips-and-troubleshooting)
* [Summary of scopes to select](/integrations/vcs/azure-devops#summary-of-scopes-to-select)

---


# ORIGEM: https://docs.wynxx.app/api/code-tester
Gft.Ai.Impact.Api.WebApi

# CodeTester

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/ai/test

### Request Body

* `SourceCodeLanguage`string
* `TestType`string
* `TestingFrameworks`string
* `ReferenceFiles`string[]
* `ExistingTests`string[]
* `WorkFolder`string
* `AdditionalInstructions`string
* `Files`string[]
* `JobName`string
* `RunName`string
* `TargetExtension`string
* `SearchPattern`string
* `PromptId`string
* `Llm`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `LlmTools`string[]
* `Conventions`string
* `ResponseJsonFormat`string
* `ResponseLanguage`string

### Responses

Success

string

POST /ai/test

```
curl --request POST \
  --url __API_BASE_URL__/ai/test \
  --header 'Content-Type: application/json' \
  --data '
{
  "SourceCodeLanguage": "SourceCodeLanguage",
  "TestType": "TestType",
  "TestingFrameworks": "TestingFrameworks",
  "ReferenceFiles": [
    "string"
  ],
  "ExistingTests": [
    "string"
  ],
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "SourceCodeLanguage": "SourceCodeLanguage",
  "TestType": "TestType",
  "TestingFrameworks": "TestingFrameworks",
  "ReferenceFiles": [
    "string"
  ],
  "ExistingTests": [
    "string"
  ],
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
string
```

plain

text/plainapplication/jsontext/json

---

[CodeReviewer](/api/code-reviewer)[Cost](/api/cost)

---


# ORIGEM: https://docs.wynxx.app/guides/features/functional-tester-web
Features

# Functional Tester Web

Generate automated functional test code from Gherkin scenarios.

## Purpose

The Functional Tester Web uses AI to translate Gherkin test scenarios into executable Playwright test automation code. It converts natural language test descriptions into ready-to-run test scripts for web applications.

## Supported Platforms

* **Platform**: Web applications
* **Test Framework**: Playwright (Node.js implementation)

## How to Use

### Required Fields

1. **LLM** - Select the AI model for code generation from the dropdown
2. **Page Address** - Enter the complete URL of the page to test (e.g., `https://www.gft.com/pl/pl/`)
3. **Test Definition** - Write your test scenario using Gherkin syntax (Given-When-Then format)

![Functional Tester Web Interface](/assets/interface-DGlBTYCO.png)

### Steps to Generate Tests

1. **Configure Settings**:

   * Choose an LLM model from the dropdown
   * Enter the target page URL
   * Write your test scenario in Gherkin format
2. **Generate Code**:

   * Click the "Start" button to begin code generation
   * Monitor the progress indicator
3. **Review Results**:

   * Check the **Diagnostic Information** section for generation status
   * View the generated Playwright code in the **Generated Code** section

## Example Scenario

```
Code



Given open the page
When user clicks on 'EN' button
Then url changes to https://www.gft.com/pl/en/
```

## Results

![Functional Tester Web Results](/assets/results-Co0Wy-Ag.png)

### Successful Generation

* **Diagnostic Information**: Shows successful code generation and test result details
* **Generated Code**: Contains the complete Playwright automation test code

### Error Handling

If something goes wrong (e.g., missing Gherkin keywords), you'll receive error messages in both the Diagnostic Information and Generated Code sections.

## Gherkin Best Practices

### Structure

* **Given**: Set up the initial state (e.g., "Given open the page")
* **When**: Define the action (e.g., "When user clicks on 'EN' button")
* **Then**: Specify the expected result (e.g., "Then url changes to [https://www.gft.com/pl/en/](https://www.gft.com/pl/en/)")

### Common Keywords

* **Navigation**: "open the page", "navigate to", "go to"
* **Interactions**: "clicks on", "enters", "selects", "types"
* **Validations**: "url changes to", "text contains", "element is visible"

## Tips for Success

* Use complete, accessible URLs including protocol (https://)
* Write clear, specific Gherkin scenarios with proper Given-When-Then structure
* Include all required keywords in your scenario
* Choose appropriate LLM models for optimal code generation results

Last modified on February 26, 2026

[Code Tester](/guides/features/code-tester)[Story Creator 1.0 [Legacy]](/guides/features/story-creator)

On this page

* [Purpose](/guides/features/functional-tester-web#purpose)
* [Supported Platforms](/guides/features/functional-tester-web#supported-platforms)
* [How to Use](/guides/features/functional-tester-web#how-to-use)
  + [Required Fields](/guides/features/functional-tester-web#required-fields)
  + [Steps to Generate Tests](/guides/features/functional-tester-web#steps-to-generate-tests)
* [Example Scenario](/guides/features/functional-tester-web#example-scenario)
* [Results](/guides/features/functional-tester-web#results)
  + [Successful Generation](/guides/features/functional-tester-web#successful-generation)
  + [Error Handling](/guides/features/functional-tester-web#error-handling)
* [Gherkin Best Practices](/guides/features/functional-tester-web#gherkin-best-practices)
  + [Structure](/guides/features/functional-tester-web#structure)
  + [Common Keywords](/guides/features/functional-tester-web#common-keywords)
* [Tips for Success](/guides/features/functional-tester-web#tips-for-success)

---


# ORIGEM: https://docs.wynxx.app/api/audit-ai
Gft.Ai.Impact.Api.WebApi

# AuditAi

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/ai/auditai

### Request Body

* `DocumentationFormat`string
* `SourceCodeLanguage`string
* `DocumentationAudience`string
* `ReferenceFiles`string[]
* `WorkFolder`string
* `AdditionalInstructions`string
* `Files`string[]
* `JobName`string
* `RunName`string
* `TargetExtension`string
* `SearchPattern`string
* `PromptId`string
* `Llm`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `LlmTools`string[]
* `Conventions`string
* `ResponseJsonFormat`string
* `ResponseLanguage`string

### Responses

Success

No data returned

POST /ai/auditai

```
curl --request POST \
  --url __API_BASE_URL__/ai/auditai \
  --header 'Content-Type: application/json' \
  --data '
{
  "DocumentationFormat": "DocumentationFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "DocumentationAudience": "DocumentationAudience",
  "ReferenceFiles": [
    "string"
  ],
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "DocumentationFormat": "DocumentationFormat",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "DocumentationAudience": "DocumentationAudience",
  "ReferenceFiles": [
    "string"
  ],
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
No example
```

plain

---

[Ai](/api/ai)[CodeDiagrammer](/api/code-diagrammer)

---


# ORIGEM: https://docs.wynxx.app/api/cost
Gft.Ai.Impact.Api.WebApi

# Cost

Endpoint:`__API_BASE_URL__`

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/jobType

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/cost/jobType

```
curl --request GET \
  --url __API_BASE_URL__/bff/cost/jobType
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
{
  "id": {},
  "name": {}
}
```

plain

text/plainapplication/jsontext/json

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/llmType

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/cost/llmType

```
curl --request GET \
  --url __API_BASE_URL__/bff/cost/llmType
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
{
  "id": {},
  "name": {}
}
```

plain

text/plainapplication/jsontext/json

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/llm

### Responses

Success

* `id`string | null
* `name`string | null

GET /bff/cost/llm

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/account-level/jobType

### query Parameters

* `JobType`string · style: form
* `Team`string · style: form
* `Llm`string · style: form
* `StartAt`string · date-time · style: form
* `EndAt`string · date-time · style: form
* `TimezoneOffSet`integer · int32 · style: form

### Responses

Success

* `total`number · double · readOnly
* `budget`number · double
* `groups`object | null

GET /bff/cost/account-level/jobType

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/account-level/team

### query Parameters

* `JobType`string · style: form
* `Team`string · style: form
* `Llm`string · style: form
* `StartAt`string · date-time · style: form
* `EndAt`string · date-time · style: form
* `TimezoneOffSet`integer · int32 · style: form

### Responses

Success

* `total`number · double · readOnly
* `budget`number · double
* `groups`object | null

GET /bff/cost/account-level/team

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/account-level/time

### query Parameters

* `JobType`string · style: form
* `Team`string · style: form
* `Llm`string · style: form
* `StartAt`string · date-time · style: form
* `EndAt`string · date-time · style: form
* `TimezoneOffSet`integer · int32 · style: form

### Responses

Success

* `period`string | null
* `total`number · double

GET /bff/cost/account-level/time

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/user-level

### query Parameters

* `UserId`string · style: form
* `UserName`string · style: form
* `PageNumber`integer · int32 · style: form
* `PageSize`integer · int32 · style: form
* `SortBy`string · style: form
* `SortDescending`boolean · style: form
* `JobType`string · style: form
* `Team`string · style: form
* `Llm`string · style: form
* `StartAt`string · date-time · style: form
* `EndAt`string · date-time · style: form
* `TimezoneOffSet`integer · int32 · style: form

### Responses

Success

* `costGroupedUserItems`array | null
* `pagination`object

GET /bff/cost/user-level

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/llm-budget

### Responses

Success

* `id`string | null
* `lllmName`string | null
* `llmTypeName`string | null
* `budget`number | null · double
* `budgetPerUser`number | null · double
* `currency`string | null
* `ignoreLimit`boolean | null
* `tenantId`string | null

GET /bff/cost/llm-budget

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/llm-budget-user

### query Parameters

* `Id`string · style: form
* `UserId`string · style: form
* `UserName`string · style: form
* `Budget`number · double · style: form

### Responses

Success

* `id`string | null
* `userId`string | null
* `userName`string | null
* `budget`number | null · double

GET /bff/cost/llm-budget-user

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/cost/llm-budget/edit

### Request Body

* `Id`string
* `LllmName`string
* `LlmTypeName`string
* `Budget`number · double
* `BudgetPerUser`number · double
* `Currency`string
* `IgnoreLimit`boolean
* `TenantId`string

### Responses

Success

* `id`string | null
* `lllmName`string | null
* `llmTypeName`string | null
* `budget`number | null · double
* `budgetPerUser`number | null · double
* `currency`string | null
* `ignoreLimit`boolean | null
* `tenantId`string | null

PUT /bff/cost/llm-budget/edit

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/cost/llm-budget-user/edit

### Request Body

* `Id`string
* `UserId`string
* `UserName`string
* `Budget`number · double

### Responses

Success

* `id`string | null
* `lllmName`string | null
* `llmTypeName`string | null
* `budget`number | null · double
* `budgetPerUser`number | null · double
* `currency`string | null
* `ignoreLimit`boolean | null
* `tenantId`string | null

PUT /bff/cost/llm-budget-user/edit

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/user-level/details

### query Parameters

* `UserId`string · style: form
* `PageNumber`integer · int32 · style: form
* `PageSize`integer · int32 · style: form
* `JobType`string · style: form
* `Team`string · style: form
* `Llm`string · style: form
* `StartAt`string · date-time · style: form
* `EndAt`string · date-time · style: form
* `TimezoneOffSet`integer · int32 · style: form

### Responses

Success

* `userId`string | null
* `userName`string | null
* `lastConnection`string · date-time
* `mostUsedLanguage`string | null
* `mostUsedModel`string | null
* `top3Tasks`string | null
* `totalRequestTokens`string | null
* `totalResponseTokens`string | null
* `totalTokens`string | null
* `totalDuration`string | null
* `currency`string | null
* `llmCostHistory`object

GET /bff/cost/user-level/details

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/settings

### Responses

Success

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

GET /bff/cost/settings

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/cost/settings

### query Parameters

* `Id`string · style: form
* `LlmName`string · style: form
* `InputCost`number · double · style: form
* `OutputCost`number · double · style: form
* `IsCurrent`boolean · style: form

### Responses

Success

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

DELETE /bff/cost/settings

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/cost/settings/details

### query Parameters

* `Id`string · style: form
* `LlmName`string · style: form
* `InputCost`number · double · style: form
* `OutputCost`number · double · style: form
* `IsCurrent`boolean · style: form

### Responses

Success

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

GET /bff/cost/settings/details

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/cost/settings/create

### Request Body

* `Id`string
* `LlmName`string
* `InputCost`number · double
* `OutputCost`number · double
* `IsCurrent`boolean

### Responses

Success

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

POST /bff/cost/settings/create

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/cost/settings/update

### Request Body

* `Id`string
* `LlmName`string
* `InputCost`number · double
* `OutputCost`number · double
* `IsCurrent`boolean

### Responses

Success

* `id`string | null
* `llmName`string | null
* `inputCost`number | null · double
* `outputCost`number | null · double
* `isCurrent`boolean | null

PUT /bff/cost/settings/update

---

[CodeTester](/api/code-tester)[Settings](/api/settings)

---


# ORIGEM: https://docs.wynxx.app
Getting Started

# What is Wynxx?

Wynxx is a comprehensive AI development platform that accelerates software development through intelligent automation. The platform provides a suite of AI-powered tools accessible via web portal and VS Code extension.

---

## Platform Features

### Code Generation & Testing

| Feature | Description | Access |
| --- | --- | --- |
| 🧪 **[Code Tester](/guides/features/code-tester)** | Generate unit and integration tests automatically from source code | Portal, VS Code |
| 🎭 **[Functional Tester](/guides/features/functional-tester)** | Drive Playwright end-to-end tests using natural language prompts | Playwright Integration |

### Code Quality & Documentation

| Feature | Description | Access |
| --- | --- | --- |
| 📝 **[Code Documenter](/guides/features/code-documenter)** | Transform source code into clear, structured documentation | Portal, VS Code |
| 🔧 **[Code Fixer](/guides/features/code-fixer)** | Automatically fix code issues detected by SAST tools (SonarQube, etc.) | Portal |
| 🗞️ **[Code Reviewer](/api-guides/code-reviewer)** | Automated code review and feedback for pull requests | API |

### AI Assistants

| Feature | Description | Access |
| --- | --- | --- |
| 💬 **[Code Dialoguer](/guides/features/code-dialoguer)** | Interactive chat for code questions, explanations, and refactoring | Portal, VS Code |
| 🏗️ **[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)** | Generate architecture diagrams (UML, Sequence, Class) from code | VS Code |

### Product & Requirements

| Feature | Description | Access |
| --- | --- | --- |
| 🧠 **[Story Creator 2.0](/guides/features/story-creator2)** | Transform use cases into structured backlogs with epics, features, and user stories | Portal |
| 📋 **[Story Creator 1.0](/guides/features/story-creator)** | Legacy story creation interface | Portal |

### Legacy Modernization

| Feature | Description | Access |
| --- | --- | --- |
| 🔄 **[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)** | Extract business rules from legacy code (COBOL, JCL) and convert to modern languages or user stories | VS Code |

---

## Access Methods

### Web Portal

The main Wynxx web application provides access to:

* Code Documenter, Code Tester, Code Fixer
* Code Dialoguer, Story Creator
* Settings, User Management, Cost Control

### VS Code Extension (Wynxx Assist)

The [Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist) brings AI capabilities directly into your IDE:

* **Architect AI** - Generate architecture diagrams
* **Code Documenter** - Document code in-editor
* **Code Tester** - Generate tests without leaving VS Code
* **Code Dialoguer** - AI chat integrated in your workflow
* **Legacy Transformer** - Modernize legacy code

### API

All features are accessible via REST API for CI/CD integration and automation. See the [API Reference](/api) for details.

---

## Administration Features

| Feature | Description |
| --- | --- |
| 👥 **[User Control](/settings/user-control)** | Manage users, roles, and permissions |
| 👪 **[Group Control](/settings/group-control)** | Organize users into groups with shared settings |
| 💰 **[Cost Control](/settings/cost-control)** | Monitor LLM usage, set budgets, and track spending |
| ⚙️ **[Application Settings](/settings/application)** | Configure LLMs, integrations, and system settings |
| 🧙 **[Setup Wizard](/settings/wizard)** | Guided initial configuration |

---

## Integrations

### LLM Providers

| Provider | Chat Models | Embedding Models |
| --- | --- | --- |
| **Azure OpenAI** | GPT-5, GPT-4.1, GPT-4o | text-embedding-3-large |
| **AWS Bedrock** | Claude Opus 4.5, Claude Sonnet 4.5, Claude Sonnet 3.7 | amazon.titan-embed-text-v1 |
| **Google Gemini** | Gemini 2.5 Pro | text-embedding-004 |
| **LiteLLM** | Multi-provider gateway | - |

### Version Control Systems

| Platform | Supported Version |
| --- | --- |
| **GitHub** | GitHub Cloud |
| **GitLab** | API v4 (Cloud & Self-hosted) |
| **Azure DevOps** | Cloud |

### SAST Tools

| Tool | Supported Version |
| --- | --- |
| **SonarQube** | 2025.x |
| **SonarCloud** | 2025.x |

### Agile Tools

| Tool | Supported Version |
| --- | --- |
| **Jira** | Jira Cloud & Jira Server (API v3) |
| **Azure Boards** | Cloud |

---

## Quick Links

* [Prerequisites](/installation/prerequisites) - Requirements checklist
* [Installation](/installation) - Deploy Wynxx on your infrastructure
* [FAQ](/getting-started/faq) - Common questions and troubleshooting
* [API Reference](/api) - REST API documentation

Last modified on February 26, 2026

[FAQ (Frequently Asked Questions)](/getting-started/faq)

On this page

* [Platform Features](/introduction#platform-features)
  + [Code Generation & Testing](/introduction#code-generation--testing)
  + [Code Quality & Documentation](/introduction#code-quality--documentation)
  + [AI Assistants](/introduction#ai-assistants)
  + [Product & Requirements](/introduction#product--requirements)
  + [Legacy Modernization](/introduction#legacy-modernization)
* [Access Methods](/introduction#access-methods)
  + [Web Portal](/introduction#web-portal)
  + [VS Code Extension (Wynxx Assist)](/introduction#vs-code-extension-wynxx-assist)
  + [API](/introduction#api)
* [Administration Features](/introduction#administration-features)
* [Integrations](/introduction#integrations)
  + [LLM Providers](/introduction#llm-providers)
  + [Version Control Systems](/introduction#version-control-systems)
  + [SAST Tools](/introduction#sast-tools)
  + [Agile Tools](/introduction#agile-tools)
* [Quick Links](/introduction#quick-links)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/Wynxx-Assist-Legacy
Wynxx Assist Extension

# Legacy Transformer

Extract business rules and convert legacy code to modern programming languages or user stories.

**How to Use**:

1. **Select Target File**: Choose legacy code file (.cob, .cbl, .jcl, .ts) to extract business rules from
2. **Add Context Files** (optional): Include additional files that provide context for better rule extraction
3. **Configure Extraction**:
   * Select prompt: "extract\_rules" for business rule extraction
   * Choose LLM model for processing
   * Set output language for generated content
4. **Review Business Rules**: Analyze extracted rules in the generated document
5. **Select Actions**: Choose from available options:
   * **Export Selected Rules**: Export specific business rules you've selected
   * **Convert Document to User Stories**: Transform business rules into user stories
   * **Export Complete Document**: Export the entire analysis document

**Business Rules Interface**:

* **Rule Detection**: Automatically identifies and categorizes business rules (e.g., "1 found")
* **Selection Controls**: "Select All" and "Select None" options for bulk operations
* **Detailed Analysis**: Each rule shows:
  + Rule/Function/Method name and purpose
  + Functional description and business logic
  + Rule status (e.g., "Relevant for Modernization")
  + Step-by-step algorithm breakdown
* **Interactive Selection**: Choose specific rules for export or conversion

**Features**:

* Intelligent business rule extraction from legacy code
* Context-aware analysis using additional files
* User story generation from extracted business logic
* Multiple export formats and options
* Support for COBOL (.cob, .cbl), JCL (.jcl), TypeScript (.ts)
* Algorithmic breakdown of business processes

## Story Creator

Transform business rules extracted from legacy code into well-structured user stories and requirements documentation.

**How to Use**:

1. First, use Legacy Transformer to extract business rules from your legacy code
2. In the business rules interface, review and select relevant rules
3. Click "Convert Document to User Stories"
4. Review automatically generated user stories based on extracted business logic
5. Export the generated stories for project management tools

**Integration with Legacy Transformer**:

* Works exclusively through the Legacy Transformer workflow
* Converts extracted business rules into actionable user stories
* Maintains traceability between legacy code logic and modern requirements
* Preserves business context during modernization projects
* Bridges gap between technical analysis and product management

**Generated Content Structure**:

* **User Stories**: Written in standard "As a... I want... So that..." format
* **Acceptance Criteria**: Detailed conditions for story completion
* **Business Context**: Background information from analyzed legacy code
* **Requirements Traceability**: Direct links back to original business rules
* **Priority Indicators**: Relevance for modernization efforts based on rule analysis

**Features**:

* AI-powered story generation from legacy code business rules
* Context-aware acceptance criteria creation
* Seamless integration with business rule extraction
* Multiple output formats for different project management tools
* Automated requirement documentation for modernization projects
* Legacy-to-modern requirements transformation

## Tips

* Use keyboard shortcuts for faster access to extension features
* Configure custom templates for consistent documentation style
* Regular updates ensure access to latest AI improvements
* Set up workspace-specific configurations for different projects

### Related resources

* [Installation](/wynxx-assist-extension/installation)
* [Configuration](/wynxx-assist-extension/configuration)
* [Troubleshooting](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[Code Documenter](/wynxx-assist-extension/Wynxx-Assist-Documentation)[Code Tester](/wynxx-assist-extension/Wynxx-Assist-Test)

On this page

* [Story Creator](/wynxx-assist-extension/Wynxx-Assist-Legacy#story-creator)
* [Tips](/wynxx-assist-extension/Wynxx-Assist-Legacy#tips)
  + [Related resources](/wynxx-assist-extension/Wynxx-Assist-Legacy#related-resources)

---


# ORIGEM: https://docs.wynxx.app/guides/features/code-tester
Features

# Code Tester

The Code Tester helps you generate unit tests from your source code in 3 simple steps. This guide mirrors the in‑app tutorial and uses the same screen flow and wording.

## When to use it

* Quickly bootstrap a test suite for existing code
* Improve coverage and catch edge cases
* Standardize tests across languages and frameworks

## Step‑by‑step tutorial (3 steps)

### 1) Configure parameters

Go to the Parameters section and set:

* Source Language: choose the language of your source code
* Prompt: use CreateUnitTests\_V1 (recommended)
* LLM: pick your preferred language model
* Reference file: optionally select a file to use as a reference for the test style
* Existing Test: optionally select an existing test file to guide structure and patterns
* Test Framework: select the framework you want to use (e.g., Jest, Mocha, Pytest, JUnit)

![Wynxx documenter Parameters](/assets/parameters-DxMBjlaJ.png)

Tips

* Start with a small, focused unit (a function or class)
* Provide short context comments in the source to improve test intent

#### Prompt options

Choose a prompt based on your testing goal. For general unit tests with broad coverage, use CreateUnitTests\_V1 (recommended). For other scenarios, see the table below:

| Prompt name | What it does |
| --- | --- |
| CreateIntegrationTests\_V1 | Generates integration tests in the specified language and framework |
| CreateUnitTests\_Chain\_V1 | Generates chained unit tests following specific instructions |
| CreateUnitTests\_V1 | Creates comprehensive unit tests with high coverage |

### 2) Preview the result

Click Preview to generate a draft set of tests. Use the preview to verify:

* Assertions reflect the intended behavior and edge cases
* The style matches your Test Framework and conventions
* Any mocks/stubs look realistic and minimal

![Wynxx Code Tester Preview](/assets/preview-CzhluunV.png)

If the preview needs adjustments, refine Parameters (Prompt, Reference file, Existing Test, or Framework) and try again.

### 3) Generate and run

When satisfied with the preview, generate the final tests.

After generation you can typically:

* Copy the tests to your clipboard
* Download them (for example, as files in the selected framework format)
* Save or share using options available in your workspace
* Run the tests locally using your chosen Test Framework

Note: Available actions may vary based on your workspace settings and integrations.

## What gets generated

Depending on your input and settings, the output may include:

* Unit tests that exercise happy paths and edge cases
* Minimal mocks/stubs for external dependencies
* Table‑driven cases (where appropriate)
* Clear, descriptive test names and assertions

## Minimal example

Input (JavaScript)

```
JavascriptCode



// math-utils.js
export function divide(a, b) {
  if (b === 0) throw new Error('Division by zero');
  return a / b;
}
```

Possible output (excerpt)

```
JavascriptCode



// math-utils.test.js (Jest)
import { divide } from './math-utils';

describe('divide', () => {
  it('divides positive numbers', () => {
    expect(divide(6, 3)).toBe(2);
  });

  it('throws on division by zero', () => {
    expect(() => divide(1, 0)).toThrow('Division by zero');
  });
});
```

## Best practices

* Prefer small, focused units for higher‑quality tests
* Name tests to describe behavior, not implementation
* Keep tests independent and deterministic
* Use the lightest viable mocking approach

## Troubleshooting

* Tests are too generic

  + Use CreateUnitTests\_V1 and select a Reference file/Existing Test to guide style
  + Provide a smaller snippet of code or add brief intent comments
* Coverage is low

  + Add edge cases (null/undefined, boundaries, error paths)
  + Split large modules and generate tests per unit
* Flaky tests

  + Avoid time‑dependent assertions; use fake timers or explicit waits
  + Ensure proper teardown of mocks/resources between tests

## Related features

* [Code Reviewer](/api-guides/code-reviewer)
* [Functional Tester](/guides/features/functional-tester)
* [Code Fixer](/guides/features/code-fixer)

Last modified on February 26, 2026

[Code Fixer](/guides/features/code-fixer)[Functional Tester Web](/guides/features/functional-tester-web)

On this page

* [When to use it](/guides/features/code-tester#when-to-use-it)
* [Step‑by‑step tutorial (3 steps)](/guides/features/code-tester#stepbystep-tutorial-3-steps)
  + [1) Configure parameters](/guides/features/code-tester#1-configure-parameters)
  + [2) Preview the result](/guides/features/code-tester#2-preview-the-result)
  + [3) Generate and run](/guides/features/code-tester#3-generate-and-run)
* [What gets generated](/guides/features/code-tester#what-gets-generated)
* [Minimal example](/guides/features/code-tester#minimal-example)
* [Best practices](/guides/features/code-tester#best-practices)
* [Troubleshooting](/guides/features/code-tester#troubleshooting)
* [Related features](/guides/features/code-tester#related-features)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/Wynxx-Assist-Dialoguer
Wynxx Assist Extension

# Code Dialoguer

Interactive AI chatbot for code-related conversations and assistance within VS Code.

**Quick Start Guide**

⚠️ **IMPORTANT**: MANDATORY CONFIGURATION

You won't be able to generate conversations if you don't have a project created in Code::Dialoguer. You can create one in the front end or you can create a new one like this:

**Scenario 1: Use this if you don't have a project created in Code Dialoguer**

1. Right-click on any file in your main folder.
2. Select the option "Wynxx: Init .wynxx Scaffold".

* Follow prompts to configure project settings
* Automatic .wynxx configuration file creation
  3.Give it a name (or use the default ID).

4. Go to the chat and list the created project.
5. Start interacting with the chat.

**Scenario 2: Use this if you have a project created in Code Dialoguer**

1. Open the Wynxx Dialoguer panel in your VS Code sidebar
2. Select your project from the PROJECT dropdown
3. Click "New Chat" to start a fresh conversation
4. Type your coding questions in natural language
5. Review responses with syntax highlighting and copy code snippets

💡 Tips for "Include Editor Context" Mode **(Scenario 3)**
Use this option when: you have a file you want to ask about or interact with; the AI ​​will read the file and be able to explain it to you in more detail or resolve any doubts about a specific line, quickly and thoroughly.

**Example Questions**:

* "create a simple code in Java and explain it to me"
* "explain this function in my current file"
* "generate unit tests for this method"

**Key Features**:

* Context-aware responses using your project files
* Code generation and explanation
* Checkbox to include current file context
* Multiple concurrent conversations with unique IDs
* Real-time responses with proper formatting
* **Project initialization** - Use "Wynxx: Init .wynxx Scaffold" to set up project configuration

### Related resources

* [Installation](/wynxx-assist-extension/installation)
* [Configuration](/wynxx-assist-extension/configuration)
* [Troubleshooting](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)[Code Documenter](/wynxx-assist-extension/Wynxx-Assist-Documentation)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/Wynxx-Assist-Test
Wynxx Assist Extension

# Code Tester

Unit test generation and optimization with coverage analysis.

**Key Features**

* Automated unit test generation
* Test case optimization
* Coverage analysis
* Framework-specific test patterns

**Quick Start Guide**

1.**Selection and Configuration**

* Open Visual Studio Code and locate the file folder.
* Right-click on the code file.
* Configure the generation using the wizard:
* Select the "Unit Test" request type.
* Enter the testing framework.
* Select the source code language.
* Choose the LLM (Learning Model).
* Define whether to include reference files (yes/no).

2. **Generation and Validation**

* Wait for the AI ​​to process the information.
* A new file will be created with the result.
* Review the content and validate that it was generated correctly.

### Related resources

* [Installation](/wynxx-assist-extension/installation)
* [Configuration](/wynxx-assist-extension/configuration)
* [Troubleshooting](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)[Wynxx v3.0.0 Release Notes](/releases/v3.0.0)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/installation
Wynxx Assist Extension

# About Wynxx Assist Installation

This page covers the installation process for the Wynxx Assist Extension.

## Prerequisites

* VS Code or compatible IDE
* Active Wynxx platform access
* Valid API credentials

## Install via VS Code Marketplace

1. Open your IDE's extension marketplace
2. Search for "Wynxx Assist"
3. Click Install
4. Restart your IDE when prompted
5. The extension will appear in your sidebar

![Wynxx Assist - VS Code Extension Installation](/assets/install-extension-vscode-BU2laQLc.png)

## Install via Wynxx Download

1. Download the vsix file from the Wynxx website
2. Open your IDE and go to the Extensions view
3. Click on the three-dot menu and select "Install from VSIX..."
4. Select the downloaded vsix file
5. Restart your IDE when prompted
6. The extension will appear in your sidebar

![Wynxx Assist - Extension Installation](/assets/install-extension-D0cHfyS5.png)

## Next Steps

After installation, proceed to [Configuration](/wynxx-assist-extension/configuration) to set up your extension.

Last modified on February 26, 2026

[About Wynxx Assist Configuration](/wynxx-assist-extension/configuration)[MCP Server](/wynxx-assist-extension/mcp-server)

On this page

* [Prerequisites](/wynxx-assist-extension/installation#prerequisites)
* [Install via VS Code Marketplace](/wynxx-assist-extension/installation#install-via-vs-code-marketplace)
* [Install via Wynxx Download](/wynxx-assist-extension/installation#install-via-wynxx-download)
* [Next Steps](/wynxx-assist-extension/installation#next-steps)

---


# ORIGEM: https://docs.wynxx.app/installation/cloud/gcp
Installation

# GCP GKE Prerequisites

This page provides complete prerequisites and setup instructions for deploying Wynxx on Google Cloud Platform using Google Kubernetes Engine (GKE).

## Required Tools

| Tool | Version | Installation | Verification |
| --- | --- | --- | --- |
| **gcloud CLI** | Latest | [cloud.google.com](https://cloud.google.com/sdk/docs/install) | `gcloud version` |
| **kubectl** | 1.24+ | Via gcloud: `gcloud components install kubectl` | `kubectl version --client` |
| **helm** | 3.12+ | [helm.sh](https://helm.sh/docs/intro/install/) | `helm version` |

### gcloud CLI Installation

```
TerminalCode



# macOS
brew install --cask google-cloud-sdk

# Linux
curl https://sdk.cloud.google.com | bash
exec -l $SHELL

# Windows - Download installer from:
# https://cloud.google.com/sdk/docs/install#windows
```

---

## GCP Authentication

```
TerminalCode



# Login
gcloud auth login

# Set project
gcloud config set project YOUR_PROJECT_ID

# Verify
gcloud config list
```

---

## Required GCP APIs

Enable these APIs before creating the cluster:

```
TerminalCode



PROJECT_ID="your-project-id"

gcloud services enable \
  container.googleapis.com \
  compute.googleapis.com \
  dns.googleapis.com \
  file.googleapis.com \
  --project $PROJECT_ID
```

---

## Required IAM Roles

| Role | Purpose |
| --- | --- |
| `roles/container.admin` | GKE cluster management |
| `roles/compute.admin` | Compute resources |
| `roles/iam.serviceAccountUser` | Service account usage |
| `roles/storage.admin` | Storage management |

---

## Assumptions

* A GCP project is available for deployment (customer-managed or GFT-managed).
* If installed in the customer's GCP, network connectivity between GFT and the customer environment is established as needed.
* LLM providers (Google Vertex AI, Azure OpenAI, OpenAI, Amazon Bedrock) are active with endpoints and API keys available.
* The pre-installation checklist is completed and validated by the GFT deployment team.
* If pulling images from GFT registry, outbound access to `gftai.azurecr.io:443` and `gftai.westeurope.data.azurecr.io:443` is allowed.

---

## Required Accesses and Permissions

* GCP permissions sufficient to create or administer:
  + Google Kubernetes Engine (or your chosen Kubernetes target)
  + Artifact Registry (if using customer registry)
  + Networking (VPCs, Subnets, Cloud NAT, Load Balancers, Cloud DNS if applicable)
  + Secret Manager (optional but recommended)
  + Cloud Logging/Monitoring (optional for observability)
* Access to the source code repository and CI/CD with read/write permissions.
* Access to a generative LLM provider with endpoint and token; know your model name(s).
* Access to SAST tools with endpoint and token; read-only permissions are sufficient.

---

## GKE Cluster Creation

```
TerminalCode



# Variables
CLUSTER_NAME="wynxx-cluster"
REGION="us-central1"
PROJECT_ID="your-project-id"

# Create cluster
gcloud container clusters create $CLUSTER_NAME \
  --project $PROJECT_ID \
  --region $REGION \
  --machine-type e2-standard-4 \
  --num-nodes 1 \
  --enable-autoscaling \
  --min-nodes 2 \
  --max-nodes 5 \
  --enable-ip-alias \
  --addons GcePersistentDiskCsiDriver,GcpFilestoreCsiDriver \
  --workload-pool=${PROJECT_ID}.svc.id.goog

# Get credentials
gcloud container clusters get-credentials $CLUSTER_NAME \
  --region $REGION \
  --project $PROJECT_ID

# Verify
kubectl get nodes
```

---

## GKE Addons Status Check

```
TerminalCode



# Check CSI drivers are enabled
gcloud container clusters describe $CLUSTER_NAME \
  --region $REGION \
  --project $PROJECT_ID \
  --format="get(addonsConfig)"
```

---

## GCP-Specific Storage Classes

### PD-SSD (RWO) - Database Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: pd-ssd
provisioner: pd.csi.storage.gke.io
parameters:
  type: pd-ssd
  fsType: ext4
volumeBindingMode: WaitForFirstConsumer
allowVolumeExpansion: true
reclaimPolicy: Retain
```

### Filestore (RWX) - Shared Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: filestore-sc
provisioner: filestore.csi.storage.gke.io
parameters:
  tier: standard
  network: default  # Or your VPC network name
volumeBindingMode: Immediate
allowVolumeExpansion: true
reclaimPolicy: Retain
```

---

## DNS Configuration (Cloud DNS)

```
TerminalCode



# Get the Ingress Load Balancer IP
INGRESS_IP=$(kubectl get svc -n ingress-nginx ingress-nginx-controller \
  -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

DNS_ZONE="your-dns-zone"
DOMAIN="example.com"
PREFIX="wynxx"

# Create A record for base domain
gcloud dns record-sets create ${PREFIX}.${DOMAIN}. \
  --zone=$DNS_ZONE \
  --type=A \
  --ttl=300 \
  --rrdatas=$INGRESS_IP

# Create wildcard A record
gcloud dns record-sets create "*.${PREFIX}.${DOMAIN}." \
  --zone=$DNS_ZONE \
  --type=A \
  --ttl=300 \
  --rrdatas=$INGRESS_IP
```

---

## Container Registry

* **Option A**: Pull directly from GFT ACR (allow outbound to endpoints listed above).
* **Option B**: Mirror images to your own Artifact Registry and configure credentials in Helm overrides.

---

## Authentication and Identity

* Keycloak is the reference identity provider used by Wynxx.
  + Prepare a realm (or use provided realm export) and know Issuer URL and client IDs.
  + Alternatively, integrate your corporate IdP via Keycloak (OIDC/SAML).

---

## Integrations (Endpoints and Tokens)

* **Agile tools** (e.g., GitHub, GitLab, Jira, Azure DevOps): base URL and API token with required scopes.
* **SAST tools** (e.g., SonarQube, SonarCloud, Fortify): base URL and read-only API token.

---

## VS Code Extensions

* Wynxx extensions (Code Dialoguer, Documenter, Tester, Legacy Transformer) are delivered as VS Code extensions.
* Ensure Wynxx endpoints are reachable from developer networks.
* If RAG is in scope, provision and configure the RAG backend accordingly.

---

## Network and Security

* Decide on public vs private exposure:
  + Public Load Balancer with TLS and Cloud Armor
  + Private endpoints with private DNS zones and VPC-only access
* Open required egress to LLM providers and any external systems you will integrate.

---

## What to Prepare Before Running the Installer

* kubeconfig targeting your GKE cluster
* DNS names and TLS certificates (Google-managed or your PKI) for portal and API
* A container registry and pull credentials strategy (GFT ACR or your Artifact Registry)
* LLM provider credentials and model names
* SAST/Agile integration endpoints and tokens
* Keycloak issuer URL and client IDs (or a plan to manage Keycloak on the cluster)
* The Subscription.zip and override-values.yaml adjusted for your environment

---

## GCP GKE Checklist

* gcloud CLI installed (`gcloud version`)
* kubectl installed (`kubectl version --client`)
* GCP credentials configured
* Required APIs enabled (container, compute, dns, file)
* GKE Cluster created (3+ nodes, e2-standard-4+)
* Cluster credentials obtained (`gcloud container clusters get-credentials`)
* PD CSI Driver enabled (included by default)
* Filestore CSI Driver enabled
* StorageClass `pd-ssd` created
* StorageClass `filestore-sc` created
* NGINX Ingress installed
* Cert-Manager installed
* ClusterIssuer configured
* DNS records configured (Cloud DNS or Cloudflare)
* Subscription.zip available
* ACR credentials available

---

## See Also

* [Prerequisites](/installation/prerequisites)
* [Installation Overview](/installation)
* [Step-by-step (Helm)](/installation/steps)
* [Validation](/installation/validation)

Last modified on February 26, 2026

[Azure AKS Prerequisites](/installation/cloud/azure)[Wynxx Installer](/installation/installer)

On this page

* [Required Tools](/installation/cloud/gcp#required-tools)
  + [gcloud CLI Installation](/installation/cloud/gcp#gcloud-cli-installation)
* [GCP Authentication](/installation/cloud/gcp#gcp-authentication)
* [Required GCP APIs](/installation/cloud/gcp#required-gcp-apis)
* [Required IAM Roles](/installation/cloud/gcp#required-iam-roles)
* [Assumptions](/installation/cloud/gcp#assumptions)
* [Required Accesses and Permissions](/installation/cloud/gcp#required-accesses-and-permissions)
* [GKE Cluster Creation](/installation/cloud/gcp#gke-cluster-creation)
* [GKE Addons Status Check](/installation/cloud/gcp#gke-addons-status-check)
* [GCP-Specific Storage Classes](/installation/cloud/gcp#gcp-specific-storage-classes)
  + [PD-SSD (RWO) - Database Storage](/installation/cloud/gcp#pd-ssd-rwo---database-storage)
  + [Filestore (RWX) - Shared Storage](/installation/cloud/gcp#filestore-rwx---shared-storage)
* [DNS Configuration (Cloud DNS)](/installation/cloud/gcp#dns-configuration-cloud-dns)
* [Container Registry](/installation/cloud/gcp#container-registry)
* [Authentication and Identity](/installation/cloud/gcp#authentication-and-identity)
* [Integrations (Endpoints and Tokens)](/installation/cloud/gcp#integrations-endpoints-and-tokens)
* [VS Code Extensions](/installation/cloud/gcp#vs-code-extensions)
* [Network and Security](/installation/cloud/gcp#network-and-security)
* [What to Prepare Before Running the Installer](/installation/cloud/gcp#what-to-prepare-before-running-the-installer)
* [GCP GKE Checklist](/installation/cloud/gcp#gcp-gke-checklist)
* [See Also](/installation/cloud/gcp#see-also)

---


# ORIGEM: https://docs.wynxx.app/api/ai
Gft.Ai.Impact.Api.WebApi

# Ai

Endpoint:`__API_BASE_URL__`

---

## 

GET

\_\_API\_BASE\_URL\_\_

/Ai/jobs/{jobId}/status

### path Parameters

* `jobId`string · required · style: simple

### Responses

Success

* `additionalResultFiles`array | null · readOnly
* `start`string | null · date-time
* `end`string | null · date-time
* `inputToken`integer · int32
* `outputToken`integer · int32
* `inputToOutputRatio`number | null · double · readOnly
* `duration`object
* `errors`object | null · readOnly
* `totalItemCount`integer · int32
* `processedItemCount`integer · int32
* `results`array | null · readOnly
* `processingItem`object
* `status`string · enum

  Enum values: 

  NotStarted

  Running

  Failed

  Cancelled

  CancellationRequested

  Completed

  CompletedWithErrors

GET /Ai/jobs/{jobId}/status

```
curl --request GET \
  --url __API_BASE_URL__/Ai/jobs/:jobId/status
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Example Responses

```
{
  "additionalResultFiles": {},
  "start": "2024-08-25T15:00:00Z",
  "end": "2024-08-25T15:00:00Z",
  "inputToken": 0,
  "outputToken": 0,
  "inputToOutputRatio": {},
  "duration": {
    "ticks": 0,
    "days": 0,
    "hours": 0,
    "milliseconds": 0,
    "microseconds": 0,
    "nanoseconds": 0,
    "minutes": 0,
    "seconds": 0,
    "totalDays": 0,
    "totalHours": 0,
    "totalMilliseconds": 0,
    "totalMicroseconds": 0,
    "totalNanoseconds": 0,
    "totalMinutes": 0,
    "totalSeconds": 0
  },
  "errors": {},
  "totalItemCount": 0,
  "processedItemCount": 0,
  "results": {},
  "processingItem": {
    "sourceItem": {},
    "output": {}
  },
  "status": "NotStarted"
}
```

plain

text/plainapplication/jsontext/json

---

## 

POST

\_\_API\_BASE\_URL\_\_

/Ai/jobs/{jobId}/cancel

### path Parameters

* `jobId`string · required · style: simple

### Responses

Success

string

POST /Ai/jobs/{jobId}/cancel

---

## 

GET

\_\_API\_BASE\_URL\_\_

/Ai/jobs/{jobId}/results

### path Parameters

* `jobId`string · required · style: simple

### Responses

Success

No data returned

GET /Ai/jobs/{jobId}/results

---

## 

GET

\_\_API\_BASE\_URL\_\_

/Ai/jobs/{jobId}/results/{outputName}

### path Parameters

* `jobId`string · required · style: simple
* `outputName`string · required · style: simple

### Responses

Success

No data returned

GET /Ai/jobs/{jobId}/results/{outputName}

---

[Agile](/api/agile)[AuditAi](/api/audit-ai)

---


# ORIGEM: https://docs.wynxx.app/guides/features/story-creator
Features

# Story Creator 1.0 [Legacy]

The Story Creator helps you turn a use case into a structured backlog in a few clicks. This guide mirrors the in‑app tutorial and uses the same flow shown in the UI.

## When to use it

* Capture business needs and turn them into actionable backlog items
* Generate epics, features, and user stories with acceptance criteria
* Keep requirements consistent and ready for sprint planning

## Step‑by‑step tutorial (3 steps)

### 1) Configure parameters

Go to the Parameters section and set:

* Project: choose the target level for creation (e.g., Epic, Feature, User Story, Task)
* Level: select the hierarchy level you want to generate next
* Prompt: choose your preferred prompt
* LLM: pick your preferred language model

Then provide content:

* Title: define a title for your document
* Description: add the use case details; this becomes the basis for creating the next level in the story hierarchy

Screenshot (Parameters)

![Wynxx documenter Parameters](/assets/parameters-C7gqQR44.png)

Tips

* Use clear business language; avoid implementation details
* Keep one primary objective per run to get focused results

### 2) Start the process

Click Start Process to begin generation.

![Wynxx documenter Parameters](/assets/process-Bv3xX_-S.png)

After the process finishes, you’ll be able to load the generated backlog.

### 3) Load and review the backlog

Click Load to import the generated items into the workspace.

![Wynxx documenter Parameters](/assets/load-C3abkM9I.png)

Once loaded, review the two‑panel workspace:

Left side (Backlog tree)

* Shows the backlog hierarchy (Epics → Features → Stories → Tasks)
* Actions:
  + Edit: available depending on the selected level
  + Lock: prevents deleting the item and its sublevels

Right side (Details panel)

* Edit title and description
* Review or refine acceptance criteria
* Adjust business rules and notes as needed

![Wynxx documenter Parameters](/assets/treeview-Bj5FT_Bg.png)

## What gets generated

Depending on your input and settings, the output may include:

* Backlog hierarchy at the levels you select (Epic, Feature, User Story, Task)
* User stories in standard format with acceptance criteria
* Clear, consistent titles and descriptions aligned to your prompt

## Best practices

* Use a descriptive Title and a concise, goal‑oriented Description
* Choose the correct Level to keep hierarchy clean
* Keep stories valuable, independent, and testable; add acceptance criteria

## Troubleshooting

* Nothing appears after Load

  + Wait for the process to finish before loading
  + Refine the Description for clearer intent; try a narrower scope
* The hierarchy isn’t what I expected

  + Check the selected Level and Project
  + Split large inputs into smaller runs per level
* Acceptance criteria are too generic

  + Provide business rules and edge cases in the Description
  + Edit acceptance criteria in the Details panel after generation

## Related features

* [Code Dialoguer](/guides/features/code-dialoguer)
* [Functional Tester](/guides/features/functional-tester)
* [Code Documenter](/guides/features/code-documenter)

Last modified on February 26, 2026

[Functional Tester Web](/guides/features/functional-tester-web)[Story Creator 2.0](/guides/features/story-creator2)

On this page

* [When to use it](/guides/features/story-creator#when-to-use-it)
* [Step‑by‑step tutorial (3 steps)](/guides/features/story-creator#stepbystep-tutorial-3-steps)
  + [1) Configure parameters](/guides/features/story-creator#1-configure-parameters)
  + [2) Start the process](/guides/features/story-creator#2-start-the-process)
  + [3) Load and review the backlog](/guides/features/story-creator#3-load-and-review-the-backlog)
* [What gets generated](/guides/features/story-creator#what-gets-generated)
* [Best practices](/guides/features/story-creator#best-practices)
* [Troubleshooting](/guides/features/story-creator#troubleshooting)
* [Related features](/guides/features/story-creator#related-features)

---


# ORIGEM: https://docs.wynxx.app/integrations/llms/aws-bedrock
LLMs

# AWS Bedrock

This guide shows how to configure AWS Bedrock for Wynxx: create credentials, pick a region, enable models, and find model IDs you can use.

## Prerequisites

* An AWS account
* IAM permissions to manage Bedrock access and credentials (AdministratorAccess or a scoped set that includes bedrock:\*, iam, iam)

## Step 1 — Create credentials (Access key ID and Secret access key)

You can use a dedicated IAM user or, preferably, an IAM role with short‑lived credentials. For a simple start using an IAM user:

1. Open the AWS Console → IAM → Users → Create user.
2. Name the user (e.g., `wynxx-bedrock-bot`).
3. Attach permissions. For testing you can use AdministratorAccess; for production prefer a least‑privilege policy that allows Bedrock invocation in your target region.
4. Create the user. Open the user → Security credentials → Create access key.
5. Copy both values safely:
   * Access key ID
   * Secret access key (shown only once)

Security tip: Prefer using AWS SSO or IAM Roles (STS) with short‑lived credentials in CI/CD and servers. Avoid storing long‑lived keys on developer machines.

## Step 2 — Choose and set the Region

AWS Bedrock is available in specific regions. Pick a supported region for the models you plan to use (e.g., `us-east-1` or `us-west-2`). You’ll configure this as `AWS_REGION`.

To set credentials locally with the AWS CLI:

```
TerminalCode



aws configure
```

Enter your:

* AWS Access Key ID
* AWS Secret Access Key
* Default region name (e.g., `us-east-1`)
* Default output format (e.g., `json`)

Alternatively, export environment variables:

```
TerminalCode



export AWS_ACCESS_KEY_ID="<your-access-key-id>"
export AWS_SECRET_ACCESS_KEY="<your-secret-access-key>"
export AWS_REGION="us-east-1"
```

## Step 3 — Enable model access in Bedrock

Bedrock requires you to request access to foundation models before you can invoke them.

1. Open AWS Console → Amazon Bedrock → Model access.
2. Click Request model access.
3. Select the providers/models you need (for example: Anthropic Claude, Amazon Titan, Mistral, Cohere, Meta Llama — availability varies by region and account).
4. Submit. Some models are auto‑approved; others may require a brief approval process.

You can verify your approved models later in the same Model access page.

## Step 4 — Identify model IDs

Each model has a `modelId` you’ll use when invoking Bedrock. To list available foundation models for your region via CLI:

```
TerminalCode



aws bedrock list-foundation-models --region "$AWS_REGION"
```

Look for entries like:

* `anthropic.claude-3-5-sonnet-2024-xx-xx` (example)
* `anthropic.claude-3-haiku-2024-xx-xx`
* `amazon.titan-text-lite-v1`
* `mistral.mistral-large-xxxxx`

Use the `modelId` string as your configured model in Wynxx.

## Values to configure in Wynxx

Provide these values to Wynxx (via environment variables, secrets manager, or your deployment configuration):

* AWS\_ACCESS\_KEY\_ID: your access key ID
* AWS\_SECRET\_ACCESS\_KEY: your secret access key
* AWS\_SESSION\_TOKEN: only if you use temporary credentials (STS/SSO)
* AWS\_REGION: the Bedrock region (e.g., `us-east-1`)
* BEDROCK\_MODEL\_ID: the model ID you approved (e.g., `anthropic.claude-3-haiku-2024-xx-xx`)

## Quick verification (optional)

List models (confirms region and credentials):

```
TerminalCode



aws bedrock list-foundation-models --region "$AWS_REGION" | jq '.modelSummaries[] | {providerName, modelId}'
```

If you see your chosen model in the list, you’re ready to invoke it from Wynxx.

## Troubleshooting

* AccessDeniedException: Ensure your user/role has permissions to Bedrock in the selected region and model access is approved.
* Model not found: The model may not be available in your region, or access hasn’t been granted yet.
* Signature or auth errors: Double‑check your keys, session token (if any), and that your system clock is accurate.
* Throttling/limits: Bedrock enforces TPS/quota; request limit increases if needed.

## Best practices

* Prefer IAM roles and short‑lived credentials (STS/SSO) over long‑lived access keys.
* Scope permissions to only the actions and regions you need.
* Store secrets in AWS Secrets Manager or your platform’s secret store—never commit them to source control.

Last modified on February 26, 2026

[Jira Integration](/integrations/agile/jira)[Azure OpenAI](/integrations/llms/azure-openai)

On this page

* [Prerequisites](/integrations/llms/aws-bedrock#prerequisites)
* [Step 1 — Create credentials (Access key ID and Secret access key)](/integrations/llms/aws-bedrock#step-1--create-credentials-access-key-id-and-secret-access-key)
* [Step 2 — Choose and set the Region](/integrations/llms/aws-bedrock#step-2--choose-and-set-the-region)
* [Step 3 — Enable model access in Bedrock](/integrations/llms/aws-bedrock#step-3--enable-model-access-in-bedrock)
* [Step 4 — Identify model IDs](/integrations/llms/aws-bedrock#step-4--identify-model-ids)
* [Values to configure in Wynxx](/integrations/llms/aws-bedrock#values-to-configure-in-wynxx)
* [Quick verification (optional)](/integrations/llms/aws-bedrock#quick-verification-optional)
* [Troubleshooting](/integrations/llms/aws-bedrock#troubleshooting)
* [Best practices](/integrations/llms/aws-bedrock#best-practices)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/Wynxx-Assist-Documentation
Wynxx Assist Extension

# Code Documenter

To generate your Documenter step by step, follow this quick path to convert your code into visual representations:

**Quick Start Guide**

1. **Selection and Configuration**

* Open Visual Studio Code and locate your file folder.
* Right-click on your code file.
* Configure your generation following the wizard:
* Select the Prompt Type.
* Define the Coding Language.
* Choose the LLM.
* Choose the Language.

2. **Generation and Validation**

* Wait for the AI ​​to process the information.
* A new document will be created with the result.

3. **Mermaid-Style Visualization**
   To preview the generated document:

* Press Ctrl + Shift + P.
* Type: Mermaid: Preview > Preview Diagram.

**Key Features**

* Multiple documentation formats
* Customizable templates
* Context-aware generation

### Related resources

* [Installation](/wynxx-assist-extension/installation)
* [Configuration](/wynxx-assist-extension/configuration)
* [Troubleshooting](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[Code Dialoguer](/wynxx-assist-extension/Wynxx-Assist-Dialoguer)[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)

---


# ORIGEM: https://docs.wynxx.app/settings/user-control
Settings

# User Control

This page explains how to manage Users in the User Control area of Settings. Follow the steps below to add and manage users.

## Access

* Go to User Control
* Access the Users section to manage user accounts

Here you can view or edit users:

![User Control – Main Page](/assets/user-control-B2EuIy4g.png)

## Add a User

Create and configure user accounts for your system.

Fields:

* UserName: the user's display or login name
* Email: the user's email address
* Password: the initial password for the user
* Assign to Group: select an existing group (groups can be created in the Group Control section)

Steps:

1. Open the Users section
2. Click Add User
3. Fill Username, Email, Password
4. Optionally assign the user to an existing group
5. Save to add the user

![User Control – Create user](/assets/create-user-D71GXq8o.png)

## Tips

* Ensure emails are correct
* Use strong, unique passwords for initial user setup
* Review user access and group assignments regularly for security
* Consider using descriptive usernames for easier identification

## Troubleshooting

* Duplicate name: choose a unique username
* Missing groups when assigning a user: create groups in the Group Control section first
* Validation errors: confirm required fields and email format are correct
* Password requirements: check system password policy requirements

Learn how to create groups:

* [Group Control](/settings/group-control)

Last modified on February 26, 2026

[Group Control](/settings/group-control)[Wizard Settings](/settings/wizard)

On this page

* [Access](/settings/user-control#access)
* [Add a User](/settings/user-control#add-a-user)
* [Tips](/settings/user-control#tips)
* [Troubleshooting](/settings/user-control#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/api/code-diagrammer
Gft.Ai.Impact.Api.WebApi

# CodeDiagrammer

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/CodeDiagrammer/diagrams/initiate

### Request Body

* `InputPath`string · required
* `OutputPath`string · required
* `LlmProvider`string · enum · required

  Enum values: 

  GOOGLE

  ANTHROPIC

### Responses

Success

No data returned

POST /CodeDiagrammer/diagrams/initiate

```
curl --request POST \
  --url __API_BASE_URL__/CodeDiagrammer/diagrams/initiate \
  --header 'Content-Type: application/json' \
  --data '
{
  "InputPath": "InputPath",
  "OutputPath": "OutputPath",
  "LlmProvider": "GOOGLE"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "InputPath": "InputPath",
  "OutputPath": "OutputPath",
  "LlmProvider": "GOOGLE"
}
```

plain

multipart/form-data

Example Responses

```
No example
```

plain

---

## 

POST

\_\_API\_BASE\_URL\_\_

/CodeDiagrammer/diagrams/analysis

### Responses

Success

No data returned

POST /CodeDiagrammer/diagrams/analysis

---

## 

POST

\_\_API\_BASE\_URL\_\_

/CodeDiagrammer/diagrams/generate

### Request Body

* `JobId`string · required

### Responses

Success

No data returned

POST /CodeDiagrammer/diagrams/generate

---

[AuditAi](/api/audit-ai)[CodeFixer](/api/code-fixer)

---


# ORIGEM: https://docs.wynxx.app/api/code-fixer
Gft.Ai.Impact.Api.WebApi

# CodeFixer

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/ai/fix

### Request Body

* `SastName`string
* `TypeSast`string
* `SourceCodeLanguage`string
* `SastIssueTypes`string[]
* `SastIssueSeverities`string[]
* `VcsFolderToUpdate`string
* `VcsFolderToMonitor`string
* `VcsReferenceIdLeft`string
* `VcsReferenceIdRight`string
* `VcsReferenceFromDate`string · date-time
* `VcsReferenceToDate`string · date-time
* `Vcs`object[]
* `BaseBranch`string
* `BranchName`string
* `RepoName`string
* `CreatePullRequestPerFile`boolean
* `RepoProjectId`string
* `CsvFile`string
* `CsvFileList`array[]
* `EncodingType`string
* `WorkFolder`string
* `AdditionalInstructions`string
* `Files`string[]
* `JobName`string
* `RunName`string
* `TargetExtension`string
* `SearchPattern`string
* `PromptId`string
* `Llm`string
* `ItemPreProcessors`string[]
* `ItemPostProcessors`string[]
* `ItemContentPreProcessors`string[]
* `ItemContentPostProcessors`string[]
* `JobPreProcessors`string[]
* `JobPostProcessors`string[]
* `LlmTools`string[]
* `Conventions`string
* `ResponseJsonFormat`string
* `ResponseLanguage`string

### Responses

Success

string

POST /ai/fix

```
curl --request POST \
  --url __API_BASE_URL__/ai/fix \
  --header 'Content-Type: application/json' \
  --data '
{
  "SastName": "SastName",
  "TypeSast": "TypeSast",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "SastIssueTypes": [
    "string"
  ],
  "SastIssueSeverities": [
    "string"
  ],
  "VcsFolderToUpdate": "VcsFolderToUpdate",
  "VcsFolderToMonitor": "VcsFolderToMonitor",
  "VcsReferenceIdLeft": "VcsReferenceIdLeft",
  "VcsReferenceIdRight": "VcsReferenceIdRight",
  "VcsReferenceFromDate": "2024-08-25T15:00:00Z",
  "VcsReferenceToDate": "2024-08-25T15:00:00Z",
  "Vcs": [
    {
      "name": {},
      "title": {},
      "folderToUpdate": {},
      "folderToMonitor": {},
      "referenceIdLeft": {},
      "referenceIdRight": {},
      "referenceFromDate": "2024-08-25T15:00:00Z",
      "referenceToDate": "2024-08-25T15:00:00Z"
    }
  ],
  "BaseBranch": "BaseBranch",
  "BranchName": "BranchName",
  "RepoName": "RepoName",
  "CreatePullRequestPerFile": true,
  "RepoProjectId": "RepoProjectId",
  "CsvFile": "CsvFile",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "EncodingType": "EncodingType",
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "SastName": "SastName",
  "TypeSast": "TypeSast",
  "SourceCodeLanguage": "SourceCodeLanguage",
  "SastIssueTypes": [
    "string"
  ],
  "SastIssueSeverities": [
    "string"
  ],
  "VcsFolderToUpdate": "VcsFolderToUpdate",
  "VcsFolderToMonitor": "VcsFolderToMonitor",
  "VcsReferenceIdLeft": "VcsReferenceIdLeft",
  "VcsReferenceIdRight": "VcsReferenceIdRight",
  "VcsReferenceFromDate": "2024-08-25T15:00:00Z",
  "VcsReferenceToDate": "2024-08-25T15:00:00Z",
  "Vcs": [
    {
      "name": {},
      "title": {},
      "folderToUpdate": {},
      "folderToMonitor": {},
      "referenceIdLeft": {},
      "referenceIdRight": {},
      "referenceFromDate": "2024-08-25T15:00:00Z",
      "referenceToDate": "2024-08-25T15:00:00Z"
    }
  ],
  "BaseBranch": "BaseBranch",
  "BranchName": "BranchName",
  "RepoName": "RepoName",
  "CreatePullRequestPerFile": true,
  "RepoProjectId": "RepoProjectId",
  "CsvFile": "CsvFile",
  "CsvFileList": [
    [
      "string"
    ]
  ],
  "EncodingType": "EncodingType",
  "WorkFolder": "WorkFolder",
  "AdditionalInstructions": "AdditionalInstructions",
  "Files": [
    "string"
  ],
  "JobName": "JobName",
  "RunName": "RunName",
  "TargetExtension": "TargetExtension",
  "SearchPattern": "SearchPattern",
  "PromptId": "PromptId",
  "Llm": "Llm",
  "ItemPreProcessors": [
    "string"
  ],
  "ItemPostProcessors": [
    "string"
  ],
  "ItemContentPreProcessors": [
    "string"
  ],
  "ItemContentPostProcessors": [
    "string"
  ],
  "JobPreProcessors": [
    "string"
  ],
  "JobPostProcessors": [
    "string"
  ],
  "LlmTools": [
    "string"
  ],
  "Conventions": "Conventions",
  "ResponseJsonFormat": "ResponseJsonFormat",
  "ResponseLanguage": "ResponseLanguage"
}
```

plain

multipart/form-data

Example Responses

```
string
```

plain

text/plainapplication/jsontext/json

---

[CodeDiagrammer](/api/code-diagrammer)[CodeReviewer](/api/code-reviewer)

---


# ORIGEM: https://docs.wynxx.app/integrations
Integrations

# Overview

Wynxx integrates with leading platforms for AI, version control, security analysis, and project management.

## Large Language Models (LLMs)

| Provider | Chat Models | Embedding Models |
| --- | --- | --- |
| **Azure OpenAI** | GPT-5, GPT-4.1, GPT-4o | text-embedding-3-large |
| **AWS Bedrock** | Claude Opus 4.5, Claude Sonnet 4.5, Claude Sonnet 3.7 | amazon.titan-embed-text-v1 |
| **Google Gemini** | Gemini 2.5 Pro | text-embedding-004 |
| **LiteLLM** | Multi-provider gateway | - |

**Configuration guides:**

* [Azure OpenAI](/integrations/llms/azure-openai)
* [AWS Bedrock](/integrations/llms/aws-bedrock)
* [Google Gemini](/integrations/llms/google-gemini)

## Version Control Systems (VCS)

| Platform | Supported Version |
| --- | --- |
| **GitHub** | GitHub Cloud |
| **GitLab** | API v4 (Cloud & Self-hosted) |
| **Azure DevOps** | Cloud |

**Configuration guides:**

* [GitHub](/integrations/vcs/github)
* [GitLab](/integrations/vcs/gitlab)
* [Azure DevOps](/integrations/vcs/azure-devops)

## Static Application Security Testing (SAST)

| Tool | Supported Version |
| --- | --- |
| **SonarQube** | 2025.x |
| **SonarCloud** | 2025.x |
| **CSV** | Custom reports |

**Configuration guides:**

* [SonarQube/SonarCloud](/integrations/sast/sonar)
* [CSV](/integrations/sast/csv)

## Agile Tools

| Tool | Supported Version |
| --- | --- |
| **Jira** | Jira Cloud & Jira Server (API v3) |
| **Azure Boards** | Cloud |

**Configuration guides:**

* [Jira](/integrations/agile/jira)

## Authentication Providers

| Provider | Protocol |
| --- | --- |
| **Keycloak** | OAuth2 / OIDC |

Last modified on February 26, 2026

[Wizard Settings](/settings/wizard)[Jira Integration](/integrations/agile/jira)

On this page

* [Large Language Models (LLMs)](/integrations#large-language-models-llms)
* [Version Control Systems (VCS)](/integrations#version-control-systems-vcs)
* [Static Application Security Testing (SAST)](/integrations#static-application-security-testing-sast)
* [Agile Tools](/integrations#agile-tools)
* [Authentication Providers](/integrations#authentication-providers)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/Wynxx-Assist-Architect
Wynxx Assist Extension

# Architect AI

To generate your diagrams step by step, follow this quick path to convert your code into visual representations:

## Key Features

* **Multi-Diagram Generation:** Automatically creates various types of diagrams (UseCases, Sequence, Class, etc.) from a single source.
* **AI-Powered Precision:** Flexible integration with different LLMs for accurate interpretation of your business logic.
* **Native VS Code Integration:** Streamlined workflow without leaving your text editor.
* **Smart Folder Organization:** Automatic file structuring to keep your project clean and well-documented.

## Quick Start Guide

1. **Selection and Configuration**

* Open Visual Studio Code and locate your file folder.
* Right-click on your code file.
* Configure your generation using the wizard:
* Select the Prompt Type.
* Choose the LLM.
* Define the Format.

2. **Generation and Validation**

* Wait for the AI ​​to process the information.
* A new folder will be created with the results.
* Review the contents; the diagrams are categorized into subfolders according to their type.

3. **Mermaid Style Preview**

To preview the generated diagram:

* Open the file in the folder.
* Press Ctrl + Shift + P.
* Type: Mermaid: Preview > Preview Diagram.

### Related resources

* [Installation](/wynxx-assist-extension/installation)
* [Configuration](/wynxx-assist-extension/configuration)
* [Troubleshooting](/wynxx-assist-extension/troubleshooting)

Last modified on February 26, 2026

[Troubleshooting](/wynxx-assist-extension/troubleshooting)[Code Dialoguer](/wynxx-assist-extension/Wynxx-Assist-Dialoguer)

On this page

* [Key Features](/wynxx-assist-extension/Wynxx-Assist-Architect#key-features)
* [Quick Start Guide](/wynxx-assist-extension/Wynxx-Assist-Architect#quick-start-guide)
  + [Related resources](/wynxx-assist-extension/Wynxx-Assist-Architect#related-resources)

---


# ORIGEM: https://docs.wynxx.app/api/settings
Gft.Ai.Impact.Api.WebApi

# Settings

Endpoint:`__API_BASE_URL__`

---

## 

POST

\_\_API\_BASE\_URL\_\_

/bff/settings/CreateSection

### Request Body

* `Id`string
* `Type`string
* `SubType`string
* `Name`string
* `Fields`object[]

### Responses

Success

* `id`string | null
* `createdAt`string · date-time
* `updatedAt`string · date-time
* `type`string · enum

  Enum values: 

  Jobs

  Llms

  Sast

  Vcs

  Repositories

  Agile

  UserControl
* `subType`string · enum

  Enum values: 

  DocCreator

  TestCreator

  CodeFixer

  CodeReviewer

  AgileJob

  Developer

  FuncionalTest

  OpenAi
* `key`string | null
* `name`string | null
* `nameExtension`string | null
* `fields`array | null

POST /bff/settings/CreateSection

```
curl --request POST \
  --url __API_BASE_URL__/bff/settings/CreateSection \
  --header 'Content-Type: application/json' \
  --data '
{
  "Id": "Id",
  "Type": "Type",
  "SubType": "SubType",
  "Name": "Name",
  "Fields": [
    {
      "alias": {},
      "type": {},
      "name": {},
      "value": {},
      "values": {},
      "subFields": {}
    }
  ]
}
'
```

shell

Show example in

cURLJavaScriptPythonJavaGoC#KotlinObjective-CPHPRubySwift

Request Body Example

```
{
  "Id": "Id",
  "Type": "Type",
  "SubType": "SubType",
  "Name": "Name",
  "Fields": [
    {
      "alias": {},
      "type": {},
      "name": {},
      "value": {},
      "values": {},
      "subFields": {}
    }
  ]
}
```

plain

multipart/form-data

Example Responses

```
{
  "id": {},
  "createdAt": "2024-08-25T15:00:00Z",
  "updatedAt": "2024-08-25T15:00:00Z",
  "type": "Jobs",
  "subType": "DocCreator",
  "key": {},
  "name": {},
  "nameExtension": {},
  "fields": {}
}
```

plain

text/plainapplication/jsontext/json

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/settings/UpdateSection/{id}

### path Parameters

* `id`string · required · style: simple

### Request Body

* `Id`string
* `Type`string
* `SubType`string
* `Name`string
* `Fields`object[]

### Responses

Success

* `id`string | null
* `type`string | null
* `subType`string | null
* `name`string | null
* `fields`array | null

PUT /bff/settings/UpdateSection/{id}

---

## 

PUT

\_\_API\_BASE\_URL\_\_

/bff/settings/UpdateSectionConfig/{id}

### path Parameters

* `id`string · required · style: simple

### Request Body

* `Id`string
* `Type`string
* `SubType`string
* `Name`string
* `Fields`object[]

### Responses

Success

* `id`string | null
* `type`string | null
* `subType`string | null
* `name`string | null
* `fields`array | null

PUT /bff/settings/UpdateSectionConfig/{id}

---

## 

DELETE

\_\_API\_BASE\_URL\_\_

/bff/settings/DeleteSection

### query Parameters

* `id`string · style: form

### Responses

Success

No data returned

DELETE /bff/settings/DeleteSection

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/{settingsID}/detail

### path Parameters

* `settingsID`string · required · style: simple

### Responses

Success

* `id`string | null
* `createdAt`string · date-time
* `updatedAt`string · date-time
* `type`string · enum

  Enum values: 

  Jobs

  Llms

  Sast

  Vcs

  Repositories

  Agile

  UserControl
* `subType`string · enum

  Enum values: 

  DocCreator

  TestCreator

  CodeFixer

  CodeReviewer

  AgileJob

  Developer

  FuncionalTest

  OpenAi
* `key`string | null
* `name`string | null
* `nameExtension`string | null
* `fields`array | null

GET /bff/settings/{settingsID}/detail

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/three

### Responses

Success

No data returned

GET /bff/settings/three

---

## 

GET

\_\_API\_BASE\_URL\_\_

/bff/settings/schemas/{subType}

### path Parameters

* `subType`string · required · style: simple

### Responses

Success

No data returned

GET /bff/settings/schemas/{subType}

---

[Cost](/api/cost)[SettingsSetup](/api/settings-setup)

---


# ORIGEM: https://docs.wynxx.app/settings
Settings

# Overview

Configure and manage your Wynxx installation after deployment.

## Administration

| Setting | Description |
| --- | --- |
| [**Application**](/settings/application) | Configure LLMs, VCS, SAST integrations, and system-wide settings |
| [**User Control**](/settings/user-control) | Manage users, roles, and permissions |
| [**Group Control**](/settings/group-control) | Organize users into groups with shared settings |
| [**Cost Control**](/settings/cost-control) | Monitor LLM usage, set budgets, and track spending |
| [**Setup Wizard**](/settings/wizard) | Guided initial configuration for new installations |

## Quick Access

* **Projects**: Create and manage projects for Story Creator, Code Dialoguer
* **Jobs**: Configure automation jobs for Code Reviewer, Code Fixer, Code Tester
* **Prompts**: Customize AI prompts for each feature
* **Integrations**: Connect to external tools (LLMs, VCS, SAST, Agile)

## Tips

* Always restart pods after changing credentials or secrets
* Use the Setup Wizard for first-time configuration
* Monitor Cost Control to track LLM token usage

Last modified on February 26, 2026

[Post-install Validation](/installation/validation)[Application Settings](/settings/application)

On this page

* [Administration](/settings#administration)
* [Quick Access](/settings#quick-access)
* [Tips](/settings#tips)

---


# ORIGEM: https://docs.wynxx.app/installation/cloud/azure
Installation

# Azure AKS Prerequisites

This page provides complete prerequisites and setup instructions for deploying Wynxx on Microsoft Azure using Azure Kubernetes Service (AKS).

## Required Tools

| Tool | Version | Installation | Verification |
| --- | --- | --- | --- |
| **Azure CLI** | 2.50+ | [Install Guide](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli) | `az --version` |
| **kubectl** | 1.24+ | `az aks install-cli` | `kubectl version --client` |
| **helm** | 3.12+ | [helm.sh](https://helm.sh/docs/intro/install/) | `helm version` |

### Azure CLI Installation

```
TerminalCode



# macOS
brew update && brew install azure-cli

# Linux (Ubuntu/Debian)
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash

# Windows
winget install -e --id Microsoft.AzureCLI
```

---

## Azure Authentication

```
TerminalCode



# Login (Device Code - Recommended for Installer)
az login --use-device-code

# Set subscription
az account set --subscription "YOUR_SUBSCRIPTION_ID"

# Verify
az account show
```

---

## Required Azure RBAC Roles

| Role | Scope | Purpose |
| --- | --- | --- |
| **Contributor** | Resource Group | Create/manage resources |
| **Azure Kubernetes Service Cluster Admin Role** | AKS Cluster | Full cluster access |
| **DNS Zone Contributor** | DNS Zone | DNS records (if using Azure DNS) |
| **Network Contributor** | VNet | Network management |

---

## Assumptions

* You have a Microsoft Azure subscription and tenant available for deployment.
* If installed in the customer's Azure, network connectivity between GFT and the customer environment is established as needed (for registry access, integrations, etc.).
* LLM providers (Azure OpenAI, OpenAI, AWS Bedrock, Google Vertex) are active with endpoints and API keys available.
* The pre-installation checklist is completed and validated by the GFT deployment team.
* If pulling images from the GFT registry, outbound access to:
  + `gftai.azurecr.io:443`
  + `gftai.westeurope.data.azurecr.io:443`

---

## Required Accesses and Permissions

* Azure subscription permissions sufficient to create or administer:
  + Azure Kubernetes Service (AKS) or the chosen Kubernetes target
  + Azure Container Registry (if using customer registry)
  + Networking (VNet, Subnets, Load Balancers, Public IPs, Private DNS Zones if applicable)
  + Azure Key Vault (optional but recommended for secret management)
  + Azure Monitor / Log Analytics (optional for observability)
* Access to the source code repository and CI/CD, with permissions to read/write repositories and pull requests (if applicable to your flow).
* Access to a generative LLM provider with endpoint and token; know the model name(s) to be used.
* Access to SAST tools with endpoint and token; read-only permissions are sufficient.

---

## AKS Cluster Creation

```
TerminalCode



# Variables
RESOURCE_GROUP="wynxx-rg"
CLUSTER_NAME="wynxx-cluster"
LOCATION="eastus"

# Create Resource Group
az group create --name $RESOURCE_GROUP --location $LOCATION

# Create AKS Cluster
az aks create \
  --resource-group $RESOURCE_GROUP \
  --name $CLUSTER_NAME \
  --location $LOCATION \
  --node-count 3 \
  --node-vm-size Standard_D4s_v3 \
  --kubernetes-version 1.29 \
  --network-plugin azure \
  --network-policy azure \
  --enable-managed-identity \
  --enable-cluster-autoscaler \
  --min-count 2 \
  --max-count 5 \
  --zones 1 2 3 \
  --tier standard

# Get Credentials
az aks get-credentials --resource-group $RESOURCE_GROUP --name $CLUSTER_NAME

# Verify
kubectl get nodes
```

---

## Azure-Specific Storage Classes

### Azure Files (RWX) - Shared Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: azurefile-csi-premium
provisioner: file.csi.azure.com
allowVolumeExpansion: true
parameters:
  skuName: Premium_LRS
  protocol: nfs
reclaimPolicy: Retain
volumeBindingMode: Immediate
```

### Azure Managed Disk (RWO) - Database Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: managed-premium-retain
provisioner: disk.csi.azure.com
allowVolumeExpansion: true
parameters:
  skuName: Premium_LRS
reclaimPolicy: Retain
volumeBindingMode: WaitForFirstConsumer
```

---

## Azure CSI Driver Verification

```
TerminalCode



# Verify Azure Files CSI Driver
az aks show --resource-group $RESOURCE_GROUP --name $CLUSTER_NAME \
  --query "storageProfile.fileCSIDriver.enabled"

# Enable if not enabled
az aks update --resource-group $RESOURCE_GROUP --name $CLUSTER_NAME \
  --enable-file-driver

# Verify pods
kubectl get pods -n kube-system -l app=csi-azurefile-controller
kubectl get pods -n kube-system -l app=csi-azuredisk-controller
```

---

## DNS Configuration (Azure DNS)

```
TerminalCode



# Get the Ingress LoadBalancer IP
INGRESS_IP=$(kubectl get svc -n ingress-nginx ingress-nginx-controller \
  -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

echo "LoadBalancer IP: $INGRESS_IP"

# Variables
DNS_RG="your-dns-resource-group"
ZONE="example.com"
PREFIX="wynxx"

# Create all required DNS records
az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name $PREFIX --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "api.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "apift.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "auth.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "apiroute.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "mfe.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "as-is.$PREFIX" --ipv4-address $INGRESS_IP

# Optional endpoints
az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "redis.$PREFIX" --ipv4-address $INGRESS_IP

az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "zipkin.$PREFIX" --ipv4-address $INGRESS_IP

# Or create a wildcard record (covers all subdomains)
az network dns record-set a add-record --resource-group $DNS_RG --zone-name $ZONE \
  --record-set-name "*.$PREFIX" --ipv4-address $INGRESS_IP
```

---

## Container Registry

* **Option A**: Use GFT ACR to pull images (requires outbound access to the endpoints above).
* **Option B**: Mirror images to your own Azure Container Registry (ACR) and configure credentials in your Helm overrides.

---

## Authentication and Identity

* Keycloak is the reference identity provider used by Wynxx. You will need:
  + A Keycloak realm (Wynxx realm export will be uploaded during installation) or an existing realm configured for Wynxx clients.
  + Public Issuer URL (e.g., `https://auth.company.com/realms/wynxx`) and client IDs to be referenced by the portal and API.
* Alternatively, integrate with your corporate IdP (via Keycloak federation or OIDC/SAML) as part of your project plan.

---

## Integrations (Endpoints and Tokens)

* **Agile tools** (e.g., Azure DevOps, Jira, GitHub):
  + Base URL (organization/project endpoints) and an API token/credential with the required scope.
* **SAST tools** (e.g., SonarQube, SonarCloud, Fortify):
  + Base URL and a read-only API token.

---

## VS Code Extensions

* Wynxx feature extensions (Code Dialoguer, Documenter, Tester, Legacy Transformer) are delivered as VS Code extensions.
* Ensure the Wynxx endpoints are reachable from developer networks.
* If you use RAG features, provision and configure the RAG environment accordingly.

---

## Network and Security

* Decide on public vs private exposure:
  + Public endpoints with WAF and TLS
  + Private endpoints using Private Link and private DNS
* Open required egress to LLM providers and any external systems you will integrate.

---

## What to Prepare Before Running the Installer

* Kubernetes context set to your AKS cluster
* DNS names and TLS certificates (or an ACME/managed certificate plan) for portal and API
* A container registry and pull credentials strategy (GFT ACR access, or your own ACR)
* LLM provider credentials and model names
* SAST/Agile integration endpoints and tokens
* Keycloak issuer URL and client IDs (or plan to deploy/manage Keycloak as part of the cluster)
* The Subscription.zip and the override-values.yaml adjusted for your environment

---

## Azure AKS Checklist

* Azure CLI installed (`az --version`)
* Logged in to Azure (`az login`)
* Subscription selected (`az account set`)
* Resource Group created
* AKS Cluster created (3+ nodes, Standard\_D4s\_v3+)
* Cluster credentials obtained (`az aks get-credentials`)
* Azure Files CSI enabled
* Azure Disk CSI enabled
* StorageClass `azurefile-csi-premium` created
* StorageClass `managed-premium-retain` created
* NGINX Ingress installed
* Cert-Manager installed
* ClusterIssuer configured
* DNS records configured
* Subscription.zip available
* ACR credentials available

---

## See Also

* [Prerequisites](/installation/prerequisites)
* [Installation Overview](/installation)
* [Step-by-step (Helm)](/installation/steps)
* [Validation](/installation/validation)

Last modified on February 26, 2026

[AWS EKS Prerequisites](/installation/cloud/aws)[GCP GKE Prerequisites](/installation/cloud/gcp)

On this page

* [Required Tools](/installation/cloud/azure#required-tools)
  + [Azure CLI Installation](/installation/cloud/azure#azure-cli-installation)
* [Azure Authentication](/installation/cloud/azure#azure-authentication)
* [Required Azure RBAC Roles](/installation/cloud/azure#required-azure-rbac-roles)
* [Assumptions](/installation/cloud/azure#assumptions)
* [Required Accesses and Permissions](/installation/cloud/azure#required-accesses-and-permissions)
* [AKS Cluster Creation](/installation/cloud/azure#aks-cluster-creation)
* [Azure-Specific Storage Classes](/installation/cloud/azure#azure-specific-storage-classes)
  + [Azure Files (RWX) - Shared Storage](/installation/cloud/azure#azure-files-rwx---shared-storage)
  + [Azure Managed Disk (RWO) - Database Storage](/installation/cloud/azure#azure-managed-disk-rwo---database-storage)
* [Azure CSI Driver Verification](/installation/cloud/azure#azure-csi-driver-verification)
* [DNS Configuration (Azure DNS)](/installation/cloud/azure#dns-configuration-azure-dns)
* [Container Registry](/installation/cloud/azure#container-registry)
* [Authentication and Identity](/installation/cloud/azure#authentication-and-identity)
* [Integrations (Endpoints and Tokens)](/installation/cloud/azure#integrations-endpoints-and-tokens)
* [VS Code Extensions](/installation/cloud/azure#vs-code-extensions)
* [Network and Security](/installation/cloud/azure#network-and-security)
* [What to Prepare Before Running the Installer](/installation/cloud/azure#what-to-prepare-before-running-the-installer)
* [Azure AKS Checklist](/installation/cloud/azure#azure-aks-checklist)
* [See Also](/installation/cloud/azure#see-also)

---


# ORIGEM: https://docs.wynxx.app/api-guides
API (How-To)

# Overview

# API Guides

Learn how to use Wynxx features via REST API for automation and CI/CD integration.

## Getting Started

| Guide | Description |
| --- | --- |
| [**Authentication**](/api-guides/authentication) | Obtain access tokens via Keycloak OAuth2 |
| [**Postman Quickstart**](/api-guides/postman-quickstart) | Set up Postman for API testing |

## Feature APIs

| Feature | Description |
| --- | --- |
| [**Code Reviewer**](/api-guides/code-reviewer) | Automate PR reviews via API |
| [**Code Fixer**](/api-guides/code-fixer) | Trigger automated code fixes |

## Common Patterns

### Job Submission Flow

1. **Authenticate**: `POST /auth/token` → get `access_token`
2. **Submit Job**: `POST /ai/{feature}` → get `jobId`
3. **Poll Status**: `GET /ai/jobs/{jobId}/status` → wait for `Completed`
4. **Get Results**: Check artifacts in status response or VCS

### Authentication Header

```
TerminalCode



Authorization: Bearer <access_token>
```

## Full API Reference

For complete endpoint documentation, see the [API Reference (Swagger)](/api).

Last modified on February 26, 2026

[Getting Started](/legacy-transformer-web/getting-started)[Authentication](/api-guides/authentication)

On this page

* [Getting Started](/api-guides#getting-started)
* [Feature APIs](/api-guides#feature-apis)
* [Common Patterns](/api-guides#common-patterns)
  + [Job Submission Flow](/api-guides#job-submission-flow)
  + [Authentication Header](/api-guides#authentication-header)
* [Full API Reference](/api-guides#full-api-reference)

---


# ORIGEM: https://docs.wynxx.app/api-guides/authentication
API (How-To)

# Authentication

How to obtain tokens from Keycloak (OIDC) and use them to call the APIs.

## Overview

We use Keycloak as our OIDC provider. To call the APIs, you must obtain an access token and send it in the Authorization header as a Bearer token.

* Token endpoint (realm ai-impact):
  + POST [https://auth.${INSTANCE}/realms/ai-impact/protocol/openid-connect/token](https://auth.$%7BINSTANCE%7D/realms/ai-impact/protocol/openid-connect/token)
* API header: Authorization: Bearer `<your_access_token>`

Note: for “confidential” clients, Keycloak requires a client\_secret. For “public” clients, the password grant (Resource Owner Password Credentials) only works if “Direct Access Grants” is enabled in the client.

## Option 1 — Password grant (username/password)

Recommended only for controlled automation (e.g., CI) and internal environments. In production, prefer Client Credentials (app‑to‑app) or Authorization Code (user-facing apps).

Local example with curl + jq:

```
TerminalCode



INSTANCE="your-domain"                 # e.g., dev.yourcompany.com
KC_REALM="ai-impact"
KC_CLIENT_ID="ai-impact-client"
KC_USERNAME="user@example.com"
KC_PASSWORD="your-strong-password"

TOKEN_RESPONSE=$(curl -s -X POST "https://auth.${INSTANCE}/realms/${KC_REALM}/protocol/openid-connect/token" \
	-H 'Content-Type: application/x-www-form-urlencoded' \
	-d "client_id=${KC_CLIENT_ID}" \
	-d 'grant_type=password' \
	-d "username=${KC_USERNAME}" \
	-d "password=${KC_PASSWORD}")

ACCESS_TOKEN=$(echo "$TOKEN_RESPONSE" | jq -r '.access_token')
REFRESH_TOKEN=$(echo "$TOKEN_RESPONSE" | jq -r '.refresh_token')

if [ -z "$ACCESS_TOKEN" ] || [ "$ACCESS_TOKEN" = "null" ]; then
	echo "Failed to obtain access token"
	echo "$TOKEN_RESPONSE"
	exit 1
fi

echo "Access token obtained"
```

Using the token in an API call:

```
TerminalCode



curl -sS https://api.${INSTANCE}/v1/sua-rota \
	-H "Authorization: Bearer ${ACCESS_TOKEN}" \
	-H 'Accept: application/json'
```

### GitHub Actions example (provided)

In GitHub Actions, use Secrets for username/password and a variable for the instance. The snippet below retrieves the token and exports it into the job environment as access\_token:

```
YAMLCode



steps:
	- name: Obter token no Keycloak
		run: |
			TOKEN_RESPONSE=$(curl -s -X POST 'https://auth.${{ vars.INSTANCE }}/realms/ai-impact/protocol/openid-connect/token' \
				-H 'Content-Type: application/x-www-form-urlencoded' \
				-d 'client_id=ai-impact-client' \
				-d 'grant_type=password' \
				-d 'username=${{ secrets.KEYCLOAK_USERNAME }}' \
				-d 'password=${{ secrets.KEYCLOAK_PASSWORD }}')
      
			ACCESS_TOKEN=$(echo $TOKEN_RESPONSE | jq -r '.access_token')
      
			if [ -z "$ACCESS_TOKEN" ] || [ "$ACCESS_TOKEN" == "null" ]; then
				echo "Failed to obtain access token"
				echo "$TOKEN_RESPONSE"
				exit 1
			fi
      
			echo "access_token=$ACCESS_TOKEN" >> $GITHUB_ENV
  
		- name: Call protected API
		run: |
			curl -sS 'https://api.${{ vars.INSTANCE }}/v1/sua-rota' \
				-H "Authorization: Bearer ${{ env.access_token }}" \
				-H 'Accept: application/json'
```

## Option 2 — Client Credentials (app‑to‑app)

For service-to-service integrations (no end user), use client\_credentials. The client must be “confidential” and have a configured client\_secret.

```
TerminalCode



INSTANCE="your-domain"
KC_REALM="ai-impact"
KC_CLIENT_ID="your-client-id"
KC_CLIENT_SECRET="your-client-secret"

TOKEN_RESPONSE=$(curl -s -X POST "https://auth.${INSTANCE}/realms/${KC_REALM}/protocol/openid-connect/token" \
	-H 'Content-Type: application/x-www-form-urlencoded' \
	-d "client_id=${KC_CLIENT_ID}" \
	-d "client_secret=${KC_CLIENT_SECRET}" \
	-d 'grant_type=client_credentials')

ACCESS_TOKEN=$(echo "$TOKEN_RESPONSE" | jq -r '.access_token')
```

## Refresh token

If you are using the password/authorization code flows, you also receive a refresh\_token. Exchange it for a new access\_token before it expires:

```
TerminalCode



NEW_RESPONSE=$(curl -s -X POST "https://auth.${INSTANCE}/realms/${KC_REALM}/protocol/openid-connect/token" \
	-H 'Content-Type: application/x-www-form-urlencoded' \
	-d "client_id=${KC_CLIENT_ID}" \
	-d 'grant_type=refresh_token' \
	-d "refresh_token=${REFRESH_TOKEN}")

ACCESS_TOKEN=$(echo "$NEW_RESPONSE" | jq -r '.access_token')
REFRESH_TOKEN=$(echo "$NEW_RESPONSE" | jq -r '.refresh_token')
```

## Required API headers

* Authorization: Bearer `<access_token>`
* Content-Type: application/json (for POST/PUT/PATCH with JSON)
* Accept: application/json

## Common issues

* 401 Unauthorized: missing/expired token or wrong realm/client.
* invalid\_grant (password): check username/password and whether “Direct Access Grants” is Enabled in the Keycloak client.
* invalid\_client: for “confidential” clients, include client\_secret.
* 403 Forbidden: verify roles/permissions assigned to the user/client for the resource.

## Best practices

* Prefer Client Credentials for service-to-service integrations.
* Store secrets in a secrets manager (GitHub Secrets, Key Vault, etc.).
* Keep token TTL short and rotate credentials periodically.
* Avoid exposing `${{ ... }}` outside fenced code blocks in MDX.

Last modified on February 26, 2026

[Overview](/api-guides)[Code Fixer (API)](/api-guides/code-fixer)

On this page

* [Overview](/api-guides/authentication#overview)
* [Option 1 — Password grant (username/password)](/api-guides/authentication#option-1--password-grant-usernamepassword)
  + [GitHub Actions example (provided)](/api-guides/authentication#github-actions-example-provided)
* [Option 2 — Client Credentials (app‑to‑app)](/api-guides/authentication#option-2--client-credentials-apptoapp)
* [Refresh token](/api-guides/authentication#refresh-token)
* [Required API headers](/api-guides/authentication#required-api-headers)
* [Common issues](/api-guides/authentication#common-issues)
* [Best practices](/api-guides/authentication#best-practices)

---


# ORIGEM: https://docs.wynxx.app/installation/installer-wizard
Installation

# Installation Wizard Guide

This guide walks you through every screen of the Wynxx Installer in the exact order they appear. Follow along with the installer open in your browser.



---

## Step 1: Welcome Screen

When you open the installer, you'll see the welcome screen.

![Wynxx Installer Welcome Screen](/assets/installer-initial-ui-CraFXZXj.png)

**Action:** Click **Start Installation**

---

## Step 2: Deployment Type

Choose your deployment target:

| Option | Status | Description |
| --- | --- | --- |
| **Kubernetes** | ✅ Available | Production deployment on managed Kubernetes |
| **Docker** | 🔜 Coming Soon | Single-node deployment for development |

**Action:** Select **Kubernetes**

---

## Step 3: Choose Cloud Provider

Select where Wynxx will be installed:

![Cloud Provider Selection](/assets/instalador-cloud-selection-DLtwes0E.png)

| Provider | Service | Status |
| --- | --- | --- |
| **Microsoft Azure** | AKS | ✅ Supported |
| **Amazon Web Services** | EKS | ✅ Supported |
| **Google Cloud Platform** | GKE | 🔜 Coming Soon |

**Action:** Select your cloud provider

The installation flow differs based on your choice. Follow the appropriate path below.

---

# Path A: Azure (AKS)

## Step A1: Azure Login

The installer uses Azure Device Code authentication.

![Azure Device Code Login](/assets/instalador-azure-login-CaWM81Z3.png)

### Login Process

1. The installer displays a **link** and a **code**
2. Click the link to open Microsoft's device login page
3. Enter the code shown in the installer
4. Complete your Azure login
5. Return to the installer

![Azure Login Completed](/assets/instalador-aazure-logado-DfnMzAn1.png)

### Configuration

| Field | Required | Description |
| --- | --- | --- |
| **Tenant ID** | Optional | Specify if you have multiple Azure AD tenants |

**Action:** Complete login and select your **Subscription**, then click **Continue**

---

## Step A2: Resource Group

Choose where to deploy Azure resources.

![Azure Resource Group Selection](/assets/instalador-azure-resource-group-Dg9SsJxv.png)

| Option | When to Use |
| --- | --- |
| **Select existing** | You have a pre-configured resource group |
| **Create new** | First-time installation |

**Action:** Select or create a resource group

---

## Step A3: Kubernetes Cluster

The installer lists existing AKS clusters in your resource group.

![Azure Kubernetes Cluster Creation](/assets/instalador-azure-cluster-creation-Xsifddzu.png)

### Using an Existing Cluster

If you already have a cluster configured per the [prerequisites](/installation/prerequisites):

**Action:** Select the cluster from the list

### Creating a New Cluster

If you need to create a cluster:

**Action:** Click **Create New Cluster**

| Field | Recommended Value | Notes |
| --- | --- | --- |
| **Cluster Name** | `wynxx-prod` | Descriptive name |
| **Tier** | Standard | Production workloads |
| **Node Count** | 3 | Minimum for HA |
| **VM Size** | Standard\_D4s\_v3 | 4 vCPUs, 16 GB RAM |

### Advanced Options

Enable these components (the installer can install them automatically):

| Component | Recommended | Purpose |
| --- | --- | --- |
| **NGINX Ingress** | ✅ Enable | HTTP/HTTPS routing |
| **Cert Manager** | ✅ Enable | TLS certificate management |

---

## Step A4: Cluster Audit

The installer automatically verifies your cluster has all required components.

![Azure Cluster Audit](/assets/instalador-azure-cluster-list-_-audit-Bhk0-7Tq.png)

### Audit Checks

The installer validates:

* ✅ Kubernetes version compatibility
* ✅ Node resources (CPU, memory)
* ✅ Storage classes available
* ✅ NGINX Ingress Controller
* ✅ Cert Manager
* ✅ Network policies

### If Components Are Missing

The installer shows an **Install** button for each missing component.

**Action:** Click **Install** for any missing components

### Existing Wynxx Installation

If Wynxx is already installed:

| Option | Use Case |
| --- | --- |
| **Load Config** | Upgrade existing installation |
| **Continue** | Fresh installation (will overwrite) |

---

# Path B: AWS (EKS)

## Step B1: AWS Authentication

Choose your authentication method:

![AWS Authentication](/assets/instalador-aws-login-_QZ6rRLz.png)

### Option 1: Access Keys (Recommended for First-Time)

| Field | Description | Where to Find |
| --- | --- | --- |
| **Access Key ID** | AWS access key | IAM Console → Security Credentials |
| **Secret Access Key** | AWS secret key | Shown once when creating key |
| **Session Token** | Optional | Required for temporary credentials |
| **Region** | AWS region | e.g., `us-east-1`, `eu-west-1` |

**Action:** Enter credentials and click **Configure Credentials**

![AWS Region Selection](/assets/instalador-aws-region-selection-DidriMh_.png)

### Option 2: Use Local AWS Credentials

If you mounted `~/.aws` in your Docker Compose:

**Action:** The installer will use your existing AWS CLI configuration

---

## Step B2: Select VPC

> ⚠️ **Important:** The installer does not create VPCs. You must have an existing VPC.

![AWS VPC and Cluster Selection](/assets/instalador-aws-vpc-selection-cluster-list-Be9ophLt.png)

**Action:** Select your VPC from the dropdown

Ensure your VPC has:

* At least 2 subnets in different availability zones
* Internet Gateway or NAT Gateway for outbound access
* Appropriate security groups

---

## Step B3: EKS Cluster

The installer lists existing EKS clusters in your selected VPC.

![EKS Cluster Creation](/assets/inbstalador-aws-cluster-creaton-BgwUgQFO.png)

### Using an Existing Cluster

**Action:** Select the cluster from the list

### Creating a New Cluster

**Action:** Click **Create New Cluster**

| Field | Recommended Value | Notes |
| --- | --- | --- |
| **Cluster Name** | `wynxx-prod` | Descriptive name |
| **Kubernetes Version** | 1.29 | Latest stable |
| **Instance Type** | m5.xlarge | 4 vCPUs, 16 GB RAM |
| **Node Count** | 3 | Minimum for HA |

---

## Step B4: Cluster Audit

The installer verifies 11 required components for EKS.

![EKS Cluster Audit](/assets/instalador-aws-cluster-audit-PnFCZ0tf.png)

### EKS-Specific Checks

* ✅ EBS CSI Driver
* ✅ EFS CSI Driver
* ✅ AWS Load Balancer Controller
* ✅ NGINX Ingress Controller
* ✅ Cert Manager
* ✅ CoreDNS
* ✅ kube-proxy
* ✅ VPC CNI
* ✅ Node resources
* ✅ Storage classes
* ✅ IAM OIDC provider

**Action:** Install any missing components using the provided buttons

---

# Common Steps (Azure and AWS)

## Step 4: Configuration

This is the most important screen. Configure all Wynxx settings here.

---

### Section 1: General Settings

![General Settings and URLs Configuration](/assets/instalador-configuration-general-settings-_-urls-8Gu8Tma2.png)

| Field | Description | Example |
| --- | --- | --- |
| **Client Name** | Name from your Subscription.zip | `acme` (from `acme.zip`) |
| **Namespace** | Kubernetes namespace | `wynxx` or `wynxx-prod` |
| **Language** | Default UI language | `EN`, `PT`, or `ES` |

---

### Section 2: URLs

Configure the URLs for each Wynxx service. All URLs should be under your domain.

| Service | Purpose | Example |
| --- | --- | --- |
| **Frontend** | Main web portal | `wynxx.company.com` |
| **API** | Backend API | `api.wynxx.company.com` |
| **Auth** | Keycloak authentication | `auth.wynxx.company.com` |
| **Functional Tester** | FT API endpoint | `apift.wynxx.company.com` |
| **Micro Frontend** | MFE assets | `mfe.wynxx.company.com` |

> **Tip:** Use a consistent naming pattern for easier DNS management.

---

### Section 3: Storage

![Storage and TLS Configuration](/assets/instalador-configuration-storage-_-certificate-CKEx5Sgb.png)

The installer suggests appropriate storage classes based on your cloud provider.

#### Azure Storage

| Use Case | Storage Class | Type |
| --- | --- | --- |
| Application data | Azure File | ReadWriteMany (RWX) |
| Database (MongoDB) | Azure Disk | ReadWriteOnce (RWO) |

#### AWS Storage

| Use Case | Storage Class | Type |
| --- | --- | --- |
| Application data | EFS | ReadWriteMany (RWX) |
| Database (MongoDB) | GP2 or GP3 | ReadWriteOnce (RWO) |

---

### Section 4: TLS and Certificates

#### Updating an Existing Installation

If the installer detects a previous Wynxx deployment in the selected cluster/namespace, the TLS screen shows the current detected configuration before you make changes.

![TLS - Detected current configuration and update flow](/assets/instalador-tls-update-installation-CsLg8fwg.png)

What you will see:

* Detected Current TLS Configuration panel: shows the active Issuer/ClusterIssuer, challenge type (HTTP-01 or DNS-01), the current Certificate name, associated Secret name (e.g., `wynxx-tls-secret`), and the DNS names covered.
* TLS Configuration Option selector: you may change the TLS management strategy by choosing one of the options below (create a new Issuer, use an existing ClusterIssuer via HTTP-01, or use an existing ClusterIssuer via DNS-01/wildcard).
* Management mode notice: switching the option will trigger the creation/update of the corresponding Kubernetes resources during install/upgrade.

#### New Installation

Configure HTTPS for your installation. There are three main scenarios for certificate management:

#### Option 1: Create New Issuer (HTTP-01 Challenge)

Use this option if you don't have an existing Cert Manager Issuer and want the installer to create one using Let's Encrypt and the HTTP-01 challenge.

![TLS Configuration Option 1 - New Issuer](/assets/instalador-tls-op1-create-issuer-http01-CfTm9GhI.png)

**Requirements:**

* The cluster must be accessible via port 80 for Let's Encrypt validation.
* Wildcard certificates are **not** supported with HTTP-01.
* All hostnames (Frontend, API, Auth, etc.) will be automatically added to the `dnsNames` list.

**Cluster Impact:**

* A new **Issuer** resource will be created in the installation namespace.
* A **Certificate** resource will be created, referencing the new Issuer.
* Cert-manager will create a **Secret** (named `wynxx-tls-secret`) containing the TLS certificate and private key after successful validation.
* Temporary pods and services will be created by cert-manager during the HTTP-01 challenge validation.

---

#### Option 2: Use Existing ClusterIssuer (HTTP-01 Challenge)

Select a pre-existing `ClusterIssuer` in your cluster that uses the HTTP-01 challenge.

![TLS Configuration Option 2 - Existing ClusterIssuer HTTP-01](/assets/instalador-tls-op2-cluster-issuer-http01-DIRE5BTX.png)

**Key Details:**

* Uses a `ClusterIssuer` already configured in your Kubernetes environment.
* The `dnsNames` list will include all hosts provided in the installation form.
* The TLS secret name will be set to `wynxx-tls-secret`.

**Cluster Impact:**

* Uses the specified global **ClusterIssuer** (no new Issuer created in the namespace).
* A **Certificate** resource will be created in the installation namespace.
* A **Secret** (named `wynxx-tls-secret`) will be created in the installation namespace once the challenge is completed.

---

#### Option 3: Use Existing ClusterIssuer (DNS-01 Challenge / Wildcard)

This is the recommended approach for production environments requiring wildcard certificates. It uses an existing `ClusterIssuer` configured with the DNS-01 challenge.

![TLS Configuration Option 3 - Existing ClusterIssuer DNS-01](/assets/instalador-tls-op3-cluster-issuer-dns01-Cdnwbrck.png)

**Key Details:**

* **Wildcard Support:** Automatically enables wildcard certificates.
* **DNS Names:** The list will contain the Frontend URL and its wildcard equivalent (e.g., `company.com` and `*.company.com`).

**Cluster Impact:**

* Uses a global **ClusterIssuer** with DNS-01 capabilities.
* A **Certificate** resource will be created in the installation namespace, requesting a wildcard certificate.
* A **Secret** (named `wynxx-tls-secret`) will be created in the installation namespace.
* This scenario avoids the need for opening port 80 for validation but requires proper DNS provider integration in the ClusterIssuer.

---

#### Troubleshooting TLS

If your certificates are not becoming "Ready", use the following commands to diagnose the issue:

**1. Check Certificate Status**

```
TerminalCode



kubectl get certificate -n <namespace>
kubectl describe certificate wynxx-certificate -n <namespace>
```

* **Purpose:** Shows the high-level status of the certificate.
* **Analysis:** Check the `Status` section. If `Ready` is `False`, look at the `Events` at the bottom to see which `CertificateRequest` is being processed or if there is a configuration error.

**2. Check Certificate Request**

```
TerminalCode



kubectl get certificaterequest -n <namespace>
kubectl describe certificaterequest <name> -n <namespace>
```

* **Purpose:** Certificates create `CertificateRequest` objects to actually ask the Issuer for a signed cert.
* **Analysis:** Verify if the request is `Approved` and if the `Ready` status is `True`. If it's stuck, the `Events` often show communication errors with the Issuer (e.g., invalid credentials or API timeouts).

**3. Check ACME Order and Challenge (HTTP-01 / DNS-01)**

```
TerminalCode



kubectl get order -n <namespace>
kubectl get challenge -n <namespace>
kubectl describe challenge <name> -n <namespace>
```

* **Purpose:** For Let's Encrypt (ACME), an `Order` represents the process, and `Challenge` is the specific task (creating a DNS record or an HTTP file).
* **Analysis:**
  + **HTTP-01:** If the challenge fails, check if port 80 is open and the URL is reachable from the internet.
  + **DNS-01:** If it fails, check if the DNS record was actually created in your provider and if the ClusterIssuer has the correct permissions.

**4. Check Cert-Manager Logs**

```
TerminalCode



kubectl logs -n cert-manager -l app=cert-manager
```

* **Purpose:** Shows internal errors of the cert-manager controller.
* **Analysis:** Look for permission errors, rate limits from Let's Encrypt, or networking issues preventing cert-manager from reaching the ACME server.

---

### Section 5: Container Registry

![Container Registry and LiteLLM Configuration](/assets/instalador-configuration-container-registry_-llitellm-C26zMIji.png)

Enter credentials for the Wynxx container registry:

| Field | Value |
| --- | --- |
| **URL** | `gftai.azurecr.io` |
| **Username** | Provided by Wynxx team |
| **Password** | Provided by Wynxx team |

---

### Section 6: Advanced Settings

#### LiteLLM Proxy (Recommended)

LiteLLM provides a unified interface to multiple LLM providers.

| Field | Description |
| --- | --- |
| **Master Key** | Admin API key for LiteLLM |
| **Salt Key** | Encryption salt for stored keys |

#### Edit Mode

Click to open the full `values.yaml` for manual editing. Use this for:

* Custom configurations not exposed in the UI
* Troubleshooting specific settings
* Advanced networking configurations

---

## Step 5: Download Configuration Files

> ⚠️ **Important:** Download these files before proceeding!

| File | Purpose |
| --- | --- |
| **values.yaml** | Complete Helm values for reference/backup |
| **K8s Manifests** | Kubernetes resources for debugging |

**Action:** Click download buttons for both files and save them securely

---

## Step 6: Upload Subscription Package

Upload the `Subscription.zip` file provided by the GFT/Wynxx team.

### What is Subscription.zip?

The Subscription.zip package contains your tenant-specific configuration:

| Content | Description |
| --- | --- |
| `keycloak/realm-export.json` | Keycloak realm configuration |
| `keycloak-secret/` | Authentication secrets |
| `keycloak-themes/` | Custom UI themes |
| `Subscription/prompts/` | AI prompts and configurations |

### Upload Process

1. Click **Upload Subscription.zip**
2. Select the file provided by GFT
3. Wait for validation to complete
4. The installer will extract and verify the package contents

> **Note:** The Subscription.zip filename may include your client name (e.g., `acme.zip`). This is normal.

**Action:** Upload your Subscription.zip file and wait for confirmation

---

## Step 7: Pre-Deploy Validation

The installer performs final validation checks:

* ✅ All required fields completed
* ✅ URLs are valid and unique
* ✅ Storage classes exist
* ✅ Registry credentials are valid
* ✅ TLS configuration is correct
* ✅ Subscription.zip uploaded and validated

**Action:** Review any warnings and fix issues before proceeding

---

## Step 8: Install

**Action:** Click **Install** to begin deployment

The installer will:

1. Apply Helm chart with your configuration
2. Create Kubernetes resources (Deployments, Services, ConfigMaps)
3. Configure Ingress routes
4. Set up TLS certificates
5. Start all pods

### Installation Progress

Monitor the progress bar and logs. A typical installation takes 5-10 minutes.

### If Installation Fails

1. Check the error message in the installer
2. Review pod status: `kubectl get pods -n <namespace>`
3. Check pod logs: `kubectl logs <pod-name> -n <namespace>`
4. Verify your configuration and retry

---

## Step 9: DNS Configuration

After successful installation, the installer displays:

* ✅ All configured URLs
* 📝 DNS records you need to create

### Create DNS Records

In your DNS provider, create these records:

| Type | Name | Value |
| --- | --- | --- |
| A or CNAME | `wynxx.company.com` | Load Balancer IP/hostname |
| A or CNAME | `api.wynxx.company.com` | Load Balancer IP/hostname |
| A or CNAME | `auth.wynxx.company.com` | Load Balancer IP/hostname |
| A or CNAME | `apift.wynxx.company.com` | Load Balancer IP/hostname |
| A or CNAME | `mfe.wynxx.company.com` | Load Balancer IP/hostname |

> **Tip:** If using wildcard certificates, you can create a single wildcard CNAME: `*.wynxx.company.com`

### Validate DNS

**Action:** Click **Validate** in the installer to verify DNS propagation

---

## Step 10: Installation Complete

🎉 **Congratulations!** Wynxx is now installed and ready to use.

### Access Your Installation

| Service | URL |
| --- | --- |
| **Wynxx Portal** | `https://wynxx.company.com` |
| **Keycloak Admin** | `https://auth.wynxx.company.com/admin` |

### Default Credentials

Initial admin credentials are provided in your `Subscription.zip` or by the Wynxx team.

---

## Next Steps

* [Validation Guide](/installation/validation) - Verify your installation
* [Settings Configuration](/settings) - Configure Wynxx features
* [Integrations](/integrations) - Connect LLMs, VCS, and SAST tools

Last modified on February 26, 2026

[Wynxx Installer](/installation/installer)[Configure Override-Values.yaml](/installation/override-values)

On this page

* [Step A1: Azure Login](/installation/installer-wizard#step-a1-azure-login)
  + [Login Process](/installation/installer-wizard#login-process)
  + [Configuration](/installation/installer-wizard#configuration)
* [Step A2: Resource Group](/installation/installer-wizard#step-a2-resource-group)
* [Step A3: Kubernetes Cluster](/installation/installer-wizard#step-a3-kubernetes-cluster)
  + [Using an Existing Cluster](/installation/installer-wizard#using-an-existing-cluster)
  + [Creating a New Cluster](/installation/installer-wizard#creating-a-new-cluster)
  + [Advanced Options](/installation/installer-wizard#advanced-options)
* [Step A4: Cluster Audit](/installation/installer-wizard#step-a4-cluster-audit)
  + [Audit Checks](/installation/installer-wizard#audit-checks)
  + [If Components Are Missing](/installation/installer-wizard#if-components-are-missing)
  + [Existing Wynxx Installation](/installation/installer-wizard#existing-wynxx-installation)

---


# ORIGEM: https://docs.wynxx.app/installation/validation
Installation

# Post-install Validation

After running the installer, verify the deployment:

1. Kubernetes health

* All pods in your namespace are Running/Ready.
* PVCs are Bound and PV reclaimPolicy is set to Retain (as intended by the script).

2. Ingress and endpoints

* The public URLs configured in `override-values.yaml` resolve via DNS and present TLS (if enabled).
* The web portal opens without console errors.

3. Authentication

* Login via Keycloak works using the issuer and client configured in overrides.
* User roles/claims are mapped as expected.

4. API

* The `/api` endpoint responds with 200 for health or info routes.
* Authenticated requests work when passing a valid Bearer token.

5. Navigation and content

* Sidebar/navigation shows expected sections.
* API docs and guides load correctly.

If something looks off, review the installer output and check pods logs and events in the target namespace.

Last modified on February 26, 2026

[Helm CLI Installation](/installation/steps)[Overview](/settings)

---


# ORIGEM: https://docs.wynxx.app/installation/steps
Installation

# Helm CLI Installation

> **Note:** For most users, we recommend using the [Wynxx Installer](/installation/installer) instead of manual Helm commands.

This guide describes how to install, update, and uninstall the platform in a Kubernetes cluster using the provided installer script `install.sh`.

Assumptions:

* You followed the Prerequisites page and have kubectl/Helm installed.
* Your kubeconfig is set to the target cluster/context.
* You have the files: `install.sh`, your `Subscription.zip`, and your `override-values.yaml` (see Configure override-values.yaml for details).

## 1) Install

Basic usage:

```
TerminalCode



./install.sh --install Subscription.zip override-values.yaml [extra-file]
```

Where:

* `Subscription.zip`: the configuration package to be uploaded into the cluster.
* `override-values.yaml`: your Helm overrides file with environment values (ingress hosts, TLS, image registry, secrets, identity endpoints, etc.).
* `extra-file` (optional): an additional file the script might upload if your configuration requires it.

What the script does at a high level:

* Validates inputs and the structure of `Subscription.zip`.
* Creates the required PersistentVolumeClaims (PVCs) for application data and MongoDB.
* Patches the underlying PersistentVolumes to use reclaimPolicy Retain (to protect data on uninstall).
* Creates a temporary Kubernetes Job to copy and extract the `Subscription.zip` into the cluster path (usually under `/configuration`).
* Updates the Keycloak realm export with your tenant URLs and endpoints based on overrides.
* Installs the Helm chart `gft-ai-impact` (version defined in the script) with your overrides.
* Cleans up temporary resources.

Example (macOS/zsh):

```
TerminalCode



chmod +x install.sh
./install.sh --install ./Subscription.zip ./override-values.yaml
```

## 2) Update

To apply updates (new `Subscription.zip` and/or updated `override-values.yaml`):

```
TerminalCode



./install.sh --update Subscription.zip override-values.yaml [extra-file]
```

The script will:

* Re-validate inputs.
* Re-upload and extract configuration.
* Re-apply Helm upgrade with your overrides.

## 3) Uninstall

To uninstall the release:

```
TerminalCode



./install.sh --uninstall Subscription.zip override-values.yaml
```

Notes:

* The script keeps PersistentVolumes with reclaimPolicy Retain to avoid data loss (PVCs may remain bound). You can remove them manually if desired after backup/review.
* Ensure you understand the data-retention implications before manual cleanup.

## 4) Troubleshooting tips

* Check the script output for explicit errors; it fails fast on missing inputs or invalid ZIP structure.
* Inspect Kubernetes objects if something doesn’t come up:
  + `kubectl get pods -n <your-namespace>`
  + `kubectl describe pod <pod> -n <your-namespace>`
  + `kubectl logs <pod> -n <your-namespace>`
* Validate Ingress/DNS/TLS settings in your `override-values.yaml`.
* Confirm available StorageClasses and provisioning status for PVCs.
* If Keycloak login fails, re-check the issuer URLs and client settings derived from overrides.

Last modified on February 26, 2026

[Configure Override-Values.yaml](/installation/override-values)[Post-install Validation](/installation/validation)

On this page

* [1) Install](/installation/steps#1-install)
* [2) Update](/installation/steps#2-update)
* [3) Uninstall](/installation/steps#3-uninstall)
* [4) Troubleshooting tips](/installation/steps#4-troubleshooting-tips)

---


# ORIGEM: https://docs.wynxx.app/installation/installer
Installation

# Wynxx Installer

The Wynxx Installer is a web-based tool that guides you through the complete installation process. It automates cluster creation, configuration, and deployment across Azure (AKS) and AWS (EKS).

## Overview

The Wynxx Installer provides:

* **Guided wizard interface** - Step-by-step installation with visual feedback
* **Cloud integration** - Direct login to Azure or AWS for cluster management
* **Cluster auditing** - Automatic verification of required components
* **Configuration generator** - Creates `override-values.yaml` with your settings
* **One-click deployment** - Applies Helm charts and configures all services

---

## Supported Scenarios

The Wynxx Installer is designed for **standard installation scenarios**. Before proceeding, understand what the installer supports and what it doesn't.

### ✅ Cluster Creation (Optional)

The installer can create a new Kubernetes cluster with the cloud provider's default configuration:

| Component | Azure (AKS) | AWS (EKS) |
| --- | --- | --- |
| **Cluster** | Standard AKS | Standard EKS |
| **NGINX Ingress** | ✅ Installed automatically | ✅ Installed automatically |
| **Cert Manager** | ✅ Optional (can be installed manually) | ✅ Optional (can be installed manually) |
| **Storage Classes** | Azure File, Azure Disk | EFS, GP2/GP3 |
| **Other prerequisites** | Installed via cluster audit | Installed via cluster audit |

> **Note:** Cluster creation via the installer is **optional**. You can create and configure your cluster manually before running the installer. The installer will detect the existing cluster and proceed with Wynxx installation.

### ✅ Wynxx Installation

The installer deploys the complete Wynxx platform into your cluster, including all required infrastructure:

| Component | Description |
| --- | --- |
| **MongoDB** | Document database for application data |
| **PostgreSQL** | Relational database for Keycloak |
| **Redis** | Cache and session storage |
| **Keycloak** | Identity and access management |
| **Wynxx Services** | All platform microservices |
| **Ingress Configuration** | HTTP/HTTPS routing |
| **TLS Certificates** | Automatic certificate provisioning |

### ❌ Not Supported

The installer is designed for standard scenarios. The following customizations are **not supported** through the installer:

| Customization | Alternative |
| --- | --- |
| **Custom Storage Classes** | Create before installation |
| **Non-standard networking** (service mesh, custom CNI) | Manual cluster configuration |
| **Multi-cluster deployments** | Manual deployment per cluster |
| **Air-gapped installations** | Contact Wynxx team |

For advanced customizations, use the [Helm CLI Installation](/installation/steps) method with a customized `override-values.yaml`.

---

## Installer Versions

| Version | Release Date | Supported Clouds | Notes |
| --- | --- | --- | --- |
| **1.0.0** | 2026-02 | Azure, AWS | Current stable |

## Container Image

```
Code



gftai.azurecr.io/wynxx/wynxx-installer:1.0.0-a554a51d
```

> **Note**: Contact the Wynxx team for registry credentials.

---

## Running the Installer

### Prerequisites

* Docker 24+ or Docker Desktop installed
* Access to the Wynxx container registry (gftai.azurecr.io)
* Network access to your cloud provider (Azure or AWS)
* Modern web browser (Chrome, Firefox, or Edge)

### Docker Compose Setup

Create a `docker-compose.yml` file:

```
YAMLCode



services:
  instalador-wynxx:
    image: gftai.azurecr.io/wynxx/wynxx-installer:1.0.0-a554a51d
    container_name: instalador-wynxx
    ports:
      - "3001:3001"
    environment:
      - NODE_ENV=production
      - PORT=3001
      - SESSION_SECRET=${SESSION_SECRET:-wynxx-installer-secret-change-me}
      # Host path mapping for Docker-in-Docker volume mounts
      - HOST_OUTPUT_PATH=${HOST_OUTPUT_PATH:-${PWD}/data/output}
    volumes:
      # Mount Docker socket for Docker operations (Docker-in-Docker)
      - /var/run/docker.sock:/var/run/docker.sock
      # Mount kubeconfig for Kubernetes cluster access (optional)
      - ~/.kube:/root/.kube:rw
      # Mount AWS credentials for EKS access (optional)
      - ~/.aws:/root/.aws:rw
      # Persist uploads (subscription.zip, override-values.yaml)
      - ./data/uploads:/app/uploads
      # Persist logs
      - ./data/logs:/app/logs
      # Output directory for generated Docker files (Wynxx deployment)
      - ./data/output:/app/output:rw
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:3001/api/health"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 10s
```

### Environment Variables

| Variable | Default | Description |
| --- | --- | --- |
| `NODE_ENV` | `production` | Runtime environment |
| `PORT` | `3001` | Internal server port |
| `SESSION_SECRET` | `wynxx-installer-secret-change-me` | Session encryption key (change in production) |
| `HOST_OUTPUT_PATH` | `${PWD}/data/output` | Host path for Docker-in-Docker mounts |

### Volume Mounts

| Mount | Purpose |
| --- | --- |
| `/var/run/docker.sock` | Docker-in-Docker operations |
| `~/.kube` | Kubernetes cluster access |
| `~/.aws` | AWS credentials for EKS |
| `./data/uploads` | Persisted uploads (subscription.zip, override-values.yaml) |
| `./data/logs` | Application logs |
| `./data/output` | Generated deployment files |

### Start the Installer

```
TerminalCode



# Login to the registry (first time only)
docker login gftai.azurecr.io

# Create data directories
mkdir -p data/uploads data/logs data/output

# Pull the latest image
docker compose pull

# Start the installer
docker compose up -d

# View logs
docker compose logs -f instalador-wynxx
```

### Access the Installer

Open your browser and navigate to:

```
Code



http://localhost:3001
```

You will see the welcome screen. Click **Start Installation** to begin.

---

## Stopping the Installer

When installation is complete:

```
TerminalCode



docker compose down
```

---

## Next Steps

Once the installer is running, proceed to:

* [Installation Wizard Guide](/installation/installer-wizard) - Step-by-step walkthrough of the installer screens

Last modified on February 26, 2026

[GCP GKE Prerequisites](/installation/cloud/gcp)[Installation Wizard Guide](/installation/installer-wizard)

On this page

* [Overview](/installation/installer#overview)
* [Supported Scenarios](/installation/installer#supported-scenarios)
  + [✅ Cluster Creation (Optional)](/installation/installer#-cluster-creation-optional)
  + [✅ Wynxx Installation](/installation/installer#-wynxx-installation)
  + [❌ Not Supported](/installation/installer#-not-supported)
* [Installer Versions](/installation/installer#installer-versions)
* [Container Image](/installation/installer#container-image)
* [Running the Installer](/installation/installer#running-the-installer)
  + [Prerequisites](/installation/installer#prerequisites)
  + [Docker Compose Setup](/installation/installer#docker-compose-setup)
  + [Environment Variables](/installation/installer#environment-variables)
  + [Volume Mounts](/installation/installer#volume-mounts)
  + [Start the Installer](/installation/installer#start-the-installer)
  + [Access the Installer](/installation/installer#access-the-installer)
* [Stopping the Installer](/installation/installer#stopping-the-installer)
* [Next Steps](/installation/installer#next-steps)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/mcp-server
Wynxx Assist Extension

# MCP Server

The Wynxx MCP (Model Context Protocol) Server enables seamless integration between AI assistants and the Wynxx platform. It provides a standardized interface for AI tools like GitHub Copilot, Claude Desktop, and other MCP-compatible clients to interact with Wynxx features directly from your development environment.

## What is MCP?

Model Context Protocol (MCP) is an open standard that enables AI applications to securely connect to external data sources and tools. The Wynxx MCP Server exposes Wynxx capabilities through this protocol, allowing AI assistants to:

* Generate documentation for your code
* Create unit and integration tests
* Review code and fix issues
* Manage agile workflows and use cases
* Interact with SAST tools like SonarQube

## Installation

### Prerequisites

* Node.js 18+ installed
* Access to the Wynxx npm registry (Azure DevOps feed)
* Valid Wynxx credentials (configured in your instance)

### Configure npm Registry Access

Add the Wynxx registry to your npm configuration:

```
TerminalCode



npm config set @wynxx:registry https://pkgs.dev.azure.com/gft-assets/ai-impact-feed/_packaging/ai-impact-feed/npm/registry/
```

### VS Code / GitHub Copilot Configuration

Add the following to your VS Code `settings.json` or MCP configuration file:

```
JSONCode



{
  "mcpServers": {
    "wynxx": {
      "type": "stdio",
      "command": "npx",
      "args": ["-y", "@wynxx/mcp", "--instance", "YOUR_INSTANCE.ai-impact.gft-cloud.com"],
      "env": {
        "npm_config_@wynxx:registry": "https://pkgs.dev.azure.com/gft-assets/ai-impact-feed/_packaging/ai-impact-feed/npm/registry/"
      }
    }
  }
}
```

Replace `YOUR_INSTANCE` with your Wynxx instance hostname.

### Claude Desktop Configuration

Add to your Claude Desktop configuration file (`claude_desktop_config.json`):

```
JSONCode



{
  "mcpServers": {
    "wynxx": {
      "command": "npx",
      "args": ["-y", "@wynxx/mcp", "--instance", "YOUR_INSTANCE.ai-impact.gft-cloud.com"],
      "env": {
        "npm_config_@wynxx:registry": "https://pkgs.dev.azure.com/gft-assets/ai-impact-feed/_packaging/ai-impact-feed/npm/registry/"
      }
    }
  }
}
```

## Available Features

### Authentication

The MCP server uses Keycloak-based authentication. When you first use a Wynxx tool, it will automatically authenticate using your configured credentials.

### Code Documentation

Generate comprehensive documentation for your source code.

**Available Prompts:**

| Prompt ID | Description |
| --- | --- |
| `DocumentCode_V5` | Standard code documentation with structured format |
| `DocumentCode_V5_Extended` | Extended documentation with diagrams and detailed analysis |
| `DocumentCode_CLI` | CLI-optimized Markdown documentation |
| `DocumentCode_Chain_V2` | Chained documentation for complex codebases |
| `Architecture_Summary` | High-level architecture documentation |
| `Architecture_Level1_Json` | Architecture analysis in JSON format |
| `ReleaseNotes_V1` | Generate release notes from git diffs |
| `SummaryDocument_V1` | High-level summary for documentation sets |

**Example usage with AI assistant:**

```
Code



Document this file using the Architecture_Summary prompt for a software engineer audience
```

### Code Testing

Generate unit and integration tests for your source code.

**Available Prompts:**

| Prompt ID | Description |
| --- | --- |
| `CreateUnitTests_V1` | Comprehensive unit tests with high coverage |
| `CreateUnitTests_Chain_V1` | Structured test templates with placeholders |
| `CreateIntegrationTests_V1` | Integration tests with mocking and stubbing |

**Example usage:**

```
Code



Generate unit tests for this Java class using CreateUnitTests_V1
```

### Code Review

Perform automated code reviews on pull requests.

**Workflow:**

1. Set the repository: `set repo to owner/repo`
2. Set the pull request: `set PR to 123`
3. Start the review: `run code review`

### Code Fixer

Automatically fix code issues detected by SAST tools.

**Supported SAST Tools:**

* SonarQube
* SonarCloud

**Issue Types:**

* `BUG` - Code bugs
* `VULNERABILITY` - Security vulnerabilities
* `CODE_SMELL` - Code quality issues
* `SECURITY_HOTSPOT` - Security hotspots

**Severity Levels:**

* `BLOCKER`
* `CRITICAL`
* `MAJOR`
* `MINOR`
* `INFO`

### Repository Documentation

Document an entire repository and commit the documentation to a new branch.

**Example:**

```
Code



Document repository owner/repo, branch main, create branch docs/auto-generated for Java code
```

### Agile Workflows

Manage agile use cases and work items.

**Job Types:**

| Type | Description |
| --- | --- |
| `Agile` | Standard agile workflow management |
| `AgileReCreate` | Recreate agile artifacts |
| `AgileStoryPoint` | Story point estimation |
| `AgileSyncWorkItems` | Sync work items with external tools |

### LLM Configuration

Select which LLM to use for processing.

**Available Models:**

| Provider | Models |
| --- | --- |
| Azure OpenAI | GPT4o, GPT-5-chat, GPT-4.1 |
| AWS Bedrock | Claude Opus 4.5, Claude Sonnet 4.5 |
| Google | Gemini 2.5 Pro |

**Example:**

```
Code



Set LLM to GPT4o
```

## Job Types Reference

The MCP server supports the following job types:

| Job Type | Description |
| --- | --- |
| `DocCreator` | Generate code documentation |
| `TestCreator` | Generate unit/integration tests |
| `CodeReviewer` | Review pull requests |
| `CodeFixer` | Fix SAST-detected issues |
| `Developer` | General development tasks |
| `Agile` | Agile workflow management |
| `AgileReCreate` | Recreate agile artifacts |
| `AgileStoryPoint` | Estimate story points |
| `AgileSyncWorkItems` | Sync work items |

## Common Commands

Here are some common commands you can use with your AI assistant:

### Documentation

```
Code



Document this file for a software engineer audience
Run repository documenter for owner/repo on branch main
List available documentation prompts
```

### Testing

```
Code



Generate unit tests for this file
Create integration tests using CreateIntegrationTests_V1
List available test prompts
```

### Code Review

```
Code



Set repo to owner/repo
Run code review for PR 123
```

### Code Fixing

```
Code



Start code fixer for owner/repo using sonarcloud
Fix critical bugs in this repository
```

### Configuration

```
Code



List available LLMs
Set LLM to Claude Opus 4.5
List available SAST configurations
```

## Monitoring Jobs

Long-running jobs can be monitored using the monitor commands:

```
Code



Monitor job <job-id>
Check status of the code fixer job
```

Jobs will return their status including:

* Processing progress
* Files processed
* Any errors encountered
* Final results when complete

## Troubleshooting

### Authentication Issues

If you encounter authentication errors:

1. Verify your Wynxx instance URL is correct
2. Check that your credentials are configured in Keycloak
3. Try refreshing settings: `refresh Wynxx settings`

### Registry Access Issues

If npm cannot find the `@wynxx/mcp` package:

1. Verify the npm registry is configured:

   ```
   TerminalCode



   npm config get @wynxx:registry
   ```
2. Check your Azure DevOps access permissions
3. Try authenticating to the feed:

   ```
   TerminalCode



   npm login --registry=https://pkgs.dev.azure.com/gft-assets/ai-impact-feed/_packaging/ai-impact-feed/npm/registry/
   ```

### Job Failures

If a job fails:

1. Check the job status for error messages
2. Verify the repository and branch names are correct
3. Ensure you have proper VCS permissions
4. Review the source code language setting

## Next Steps

* [Configure the VS Code Extension](/wynxx-assist-extension/configuration)
* [Code Documenter Feature](/guides/features/code-documenter)
* [Code Tester Feature](/guides/features/code-tester)
* [Code Fixer Feature](/guides/features/code-fixer)

Last modified on February 26, 2026

[About Wynxx Assist Installation](/wynxx-assist-extension/installation)[Troubleshooting](/wynxx-assist-extension/troubleshooting)

On this page

* [What is MCP?](/wynxx-assist-extension/mcp-server#what-is-mcp)
* [Installation](/wynxx-assist-extension/mcp-server#installation)
  + [Prerequisites](/wynxx-assist-extension/mcp-server#prerequisites)
  + [Configure npm Registry Access](/wynxx-assist-extension/mcp-server#configure-npm-registry-access)
  + [VS Code / GitHub Copilot Configuration](/wynxx-assist-extension/mcp-server#vs-code--github-copilot-configuration)
  + [Claude Desktop Configuration](/wynxx-assist-extension/mcp-server#claude-desktop-configuration)
* [Available Features](/wynxx-assist-extension/mcp-server#available-features)
  + [Authentication](/wynxx-assist-extension/mcp-server#authentication)
  + [Code Documentation](/wynxx-assist-extension/mcp-server#code-documentation)
  + [Code Testing](/wynxx-assist-extension/mcp-server#code-testing)
  + [Code Review](/wynxx-assist-extension/mcp-server#code-review)
  + [Code Fixer](/wynxx-assist-extension/mcp-server#code-fixer)
  + [Repository Documentation](/wynxx-assist-extension/mcp-server#repository-documentation)
  + [Agile Workflows](/wynxx-assist-extension/mcp-server#agile-workflows)
  + [LLM Configuration](/wynxx-assist-extension/mcp-server#llm-configuration)
* [Job Types Reference](/wynxx-assist-extension/mcp-server#job-types-reference)
* [Common Commands](/wynxx-assist-extension/mcp-server#common-commands)
  + [Documentation](/wynxx-assist-extension/mcp-server#documentation)
  + [Testing](/wynxx-assist-extension/mcp-server#testing)
  + [Code Review](/wynxx-assist-extension/mcp-server#code-review-1)
  + [Code Fixing](/wynxx-assist-extension/mcp-server#code-fixing)
  + [Configuration](/wynxx-assist-extension/mcp-server#configuration)
* [Monitoring Jobs](/wynxx-assist-extension/mcp-server#monitoring-jobs)
* [Troubleshooting](/wynxx-assist-extension/mcp-server#troubleshooting)
  + [Authentication Issues](/wynxx-assist-extension/mcp-server#authentication-issues)
  + [Registry Access Issues](/wynxx-assist-extension/mcp-server#registry-access-issues)
  + [Job Failures](/wynxx-assist-extension/mcp-server#job-failures)
* [Next Steps](/wynxx-assist-extension/mcp-server#next-steps)

---


# ORIGEM: https://docs.wynxx.app/integrations/agile/jira
Agile

# Jira Integration

This page explains, step by step, how to create the API tokens required by Wynxx to integrate with Jira. The instructions cover both Jira Cloud and Jira Server, and are written to be easy to follow.

## What you will create

You need an API token (Cloud) or Personal Access Token (Server) with permissions to:

* **Read and create issues** in your target projects
* **Read project metadata** (issue types, fields, workflows)
* **Manage sprints and backlogs** (if using Story Creator features)

**Security best practice**: Use a dedicated service account, set the shortest practical expiration, and store tokens securely (for example, as secrets in your secret manager).

## Before you start

### For Jira Cloud

* You must have an Atlassian account with access to your Jira Cloud instance
* Your instance URL follows the pattern: `https://your-domain.atlassian.net`
* You need project-level permissions to create and edit issues

### For Jira Server

* You must have a Jira account with appropriate project permissions
* Your administrator must have enabled Personal Access Tokens
* Your instance URL is typically: `https://jira.your-company.com`

---

## Jira Cloud: Create an API Token

### Step 1: Open Atlassian Account Settings

1. Go to [https://id.atlassian.com/manage-profile/security/api-tokens](https://id.atlassian.com/manage-profile/security/api-tokens)
2. Or navigate: Click your avatar → **Manage account** → **Security** → **API tokens**

### Step 2: Create a New Token

1. Click **Create API token**
2. Enter a descriptive label, for example: `Wynxx Integration`
3. Click **Create**
4. **Copy the token immediately** - you will not be able to view it again

### Step 3: Note Your Credentials

For Jira Cloud, you need **three pieces of information**:

| Credential | Example | Where to find |
| --- | --- | --- |
| **Email** | `user@company.com` | Your Atlassian account email |
| **API Token** | `ATATT3xFf...` | Just created above |
| **Instance URL** | `https://your-domain.atlassian.net` | Your Jira Cloud URL |

**Note**: Jira Cloud uses Basic Auth with email as credentials.

---

## Jira Server: Create a Personal Access Token

### Step 1: Open Personal Access Tokens

1. Log in to your Jira Server instance
2. Click your avatar in the top-right corner
3. Select **Profile** or **Personal Settings**
4. Navigate to **Personal Access Tokens**

> **Note**: If you don't see this option, your administrator may need to enable PATs for your instance.

### Step 2: Create a New Token

1. Click **Create token**
2. Enter a descriptive name, for example: `Wynxx Integration Token`
3. Set an expiration date (recommended: 90 days or less)
4. Click **Create**
5. **Copy the token immediately** - you will not be able to view it again

### Step 3: Note Your Credentials

For Jira Server, you need **two pieces of information**:

| Credential | Example | Where to find |
| --- | --- | --- |
| **Personal Access Token** | `MDM5MjM0NTY3...` | Just created above |
| **Instance URL** | `https://jira.your-company.com` | Your Jira Server URL |

**Note**: Jira Server PATs are used as Bearer tokens (no email required).

---

## Required Permissions

Ensure your Jira account has these permissions in the target projects:

### Minimum Required Permissions

| Permission | Purpose |
| --- | --- |
| **Browse Projects** | View project structure and issues |
| **Create Issues** | Create new epics, stories, tasks |
| **Edit Issues** | Update issue fields, descriptions |
| **Transition Issues** | Move issues through workflow states |
| **Schedule Issues** | Assign to sprints (if using sprints) |

### Recommended Permission Scheme

For a dedicated Wynxx service account, assign one of these roles:

* **Atlassian default roles**: `Developers` or `Administrators`
* **Custom role**: Create a role with the permissions listed above

---

## Using Jira with Wynxx Features

### Story Creator Integration

Once configured, you can push generated backlogs directly to Jira:

1. Create a backlog using [Story Creator Legacy](/guides/features/story-creator)
2. Review the generated epics, features, and stories
3. Click **Export to Jira**
4. Select the target project and configure mapping:
   * **Epic** → Jira Epic
   * **Feature** → Jira Story or Epic (configurable)
   * **User Story** → Jira Story
   * **Task** → Jira Sub-task

### Field Mapping

Wynxx maps generated content to Jira fields:

| Wynxx Field | Jira Field |
| --- | --- |
| Title | Summary |
| Description | Description |
| Acceptance Criteria | Custom field or Description |
| Story Points | Story Points (if configured) |
| Priority | Priority |

---

## Tips and Troubleshooting

### Common Issues

| Issue | Solution |
| --- | --- |
| **401 Unauthorized** | Verify email and token are correct. For Cloud, ensure you're using email format |
| **403 Forbidden** | Check your account has required permissions in the target project |
| **Connection timeout** | Verify the instance URL is accessible from your Wynxx deployment |
| **"API Token" option not visible** | For Server, ask your admin to enable Personal Access Tokens |
| **Project not found** | Ensure you have "Browse Projects" permission |

### Token Rotation

API tokens should be rotated regularly:

1. Create a new token before the old one expires
2. Update the configuration in Wynxx
3. Test the connection
4. Revoke the old token

### Testing Your Token

**Jira Cloud** - Test with curl:

```
TerminalCode



curl -u "email@company.com:YOUR_API_TOKEN" \
  "https://your-domain.atlassian.net/rest/api/3/myself"
```

**Jira Server** - Test with curl:

```
TerminalCode



curl -H "Authorization: Bearer YOUR_PAT" \
  "https://jira.your-company.com/rest/api/2/myself"
```

A successful response returns your user profile JSON.

---

## Summary

| Jira Version | Auth Type | Credentials Needed |
| --- | --- | --- |
| **Cloud** | API Token | Email + API Token + Instance URL |
| **Server** | Personal Access Token | PAT + Instance URL |

## Related Documentation

* [Story Creator 2.0](/guides/features/story-creator2) - Generate backlogs from use cases
* [Azure DevOps Integration](/integrations/vcs/azure-devops) - Alternative agile integration
* [Settings Overview](/settings) - Configure integrations

For reference, see Atlassian's documentation:

* [Jira Cloud API Tokens](https://support.atlassian.com/atlassian-account/docs/manage-api-tokens-for-your-atlassian-account/)
* [Jira Server Personal Access Tokens](https://confluence.atlassian.com/enterprise/using-personal-access-tokens-1026032365.html)

Last modified on February 26, 2026

[Overview](/integrations)[AWS Bedrock](/integrations/llms/aws-bedrock)

On this page

* [What you will create](/integrations/agile/jira#what-you-will-create)
* [Before you start](/integrations/agile/jira#before-you-start)
  + [For Jira Cloud](/integrations/agile/jira#for-jira-cloud)
  + [For Jira Server](/integrations/agile/jira#for-jira-server)
* [Jira Cloud: Create an API Token](/integrations/agile/jira#jira-cloud-create-an-api-token)
  + [Step 1: Open Atlassian Account Settings](/integrations/agile/jira#step-1-open-atlassian-account-settings)
  + [Step 2: Create a New Token](/integrations/agile/jira#step-2-create-a-new-token)
  + [Step 3: Note Your Credentials](/integrations/agile/jira#step-3-note-your-credentials)
* [Jira Server: Create a Personal Access Token](/integrations/agile/jira#jira-server-create-a-personal-access-token)
  + [Step 1: Open Personal Access Tokens](/integrations/agile/jira#step-1-open-personal-access-tokens)
  + [Step 2: Create a New Token](/integrations/agile/jira#step-2-create-a-new-token-1)
  + [Step 3: Note Your Credentials](/integrations/agile/jira#step-3-note-your-credentials-1)
* [Required Permissions](/integrations/agile/jira#required-permissions)
  + [Minimum Required Permissions](/integrations/agile/jira#minimum-required-permissions)
  + [Recommended Permission Scheme](/integrations/agile/jira#recommended-permission-scheme)
* [Using Jira with Wynxx Features](/integrations/agile/jira#using-jira-with-wynxx-features)
  + [Story Creator Integration](/integrations/agile/jira#story-creator-integration)
  + [Field Mapping](/integrations/agile/jira#field-mapping)
* [Tips and Troubleshooting](/integrations/agile/jira#tips-and-troubleshooting)
  + [Common Issues](/integrations/agile/jira#common-issues)
  + [Token Rotation](/integrations/agile/jira#token-rotation)
  + [Testing Your Token](/integrations/agile/jira#testing-your-token)
* [Summary](/integrations/agile/jira#summary)
* [Related Documentation](/integrations/agile/jira#related-documentation)

---


# ORIGEM: https://docs.wynxx.app/api-guides/code-fixer
API (How-To)

# Code Fixer (API)

Examples of requests/responses for the Code Fixer feature via API.

## Overview

Code Fixer performs automated code fixes based on SAST findings and/or defined patterns. It can write corrected files and open Pull Requests automatically (when configured). The basic flow is:

* Authenticate and obtain an `access_token` (see [Authentication](/api-guides/authentication)).
* Submit a job to `POST /ai/fix` with processing parameters.
* Poll job status via `GET /ai/jobs/{jobId}/status` until it returns `Completed`.
* Consume artifacts/results (URIs) returned by the job.

Before you start: In the web portal, you can review the default variables for a Code Fixer job template in Settings → Jobs → Code Fixer. See also: [Application Settings › Jobs](/settings/application#jobs).

## Authentication

* Header: `Authorization: Bearer <access_token>`
* How to get the token: see [Authentication](/api-guides/authentication).

## Endpoint

* URL: `https://api.<INSTANCE>/ai/fix`
* Method: `POST`
* Content-Type: `multipart/form-data`

### Key fields (form-data)

* `RunName`: logical execution name (e.g., `code_reviewer` or `code_fixer`).
* `SastName`: SAST project/result identifier to consider (when applicable).
* `jobName`: job name (e.g., `DemoCodeFixer`).
* `SourceCodeLanguage`: target language (e.g., `Java`).
* `PromptId`: fix prompt/strategy (e.g., `CodeFixer__FixCode_V3`).
* `TargetExtension`: target file extension (e.g., `java`).
* `Llm`: model/provider to use (e.g., value of `${{ vars.LLM }}` in GitHub Actions).
* `SearchPattern`: file search pattern (e.g., `*.java`).
* `JobPreProcessors[]`: pre-steps (e.g., `VCS-CLONE-REPO`, `READ-CODE-CONVENTIONS`, `VCS-CHECKOUT-REPO`).
* `ItemContentPostProcessors[]`: per-item post actions (e.g., `WRITE-CONTENT`, `COPY-CONTENT`, `VCS-PULL-REQUEST-FILE-REPO`).
* `JobPostProcessors[]`: end-of-job actions (e.g., `VCS-COMMIT-PUSH-REPO`, `VCS-PULL-REQUEST-REPO`).
* `IssueSeverities[]`: SAST severities to include (e.g., `BLOCKER`, `CRITICAL`, `MAJOR`).
* `SastIssueTypes[]`: SAST issue types (e.g., `BUG`, `VULNERABILITY`, `CODE_SMELL`).
* `Vcs[0][Name]`: VCS configuration name (e.g., `Codefixer`) when integrating with a repo.
* `BranchName` / `BaseBranch`: working branch and base branch for PRs.
* `RepoName`: target repository (e.g., `${{ github.repository }}`).
* `CreatePullRequestPerFile`: `true/false` to open one PR per file.

Note: processor names (`VCS-*`, `READ-*`, etc.) and prompts vary per environment configuration.

### Content type note

* Recommended: `multipart/form-data` (as shown in curl/GitHub Actions below).
* Some installations may also accept `application/x-www-form-urlencoded` (useful for quick Postman trials). If unsure, prefer `multipart/form-data`.

## Quick curl example (local)

```
TerminalCode



INSTANCE="your-domain"               # e.g., dev.yourcompany.com
ACCESS_TOKEN="your-access-token"

curl --location "https://api.${INSTANCE}/ai/fix" \
	--header "Authorization: Bearer ${ACCESS_TOKEN}" \
	--form 'RunName="code_fixer"' \
	--form 'jobName="DemoCodeFixer"' \
	--form 'SourceCodeLanguage="Java"' \
	--form 'PromptId="CodeFixer__FixCode_V3"' \
	--form 'TargetExtension="java"' \
	--form 'SearchPattern="*.java"'
```

## Postman quickstart

For general Postman setup (environment variables and how to obtain a token), see [Postman Quickstart](/api-guides/postman-quickstart). Then, in your Code Fixer request, include the form fields from Key fields above and point to `{{server}}/ai/fix` with Bearer `{{token}}`. Poll `{{server}}/ai/jobs/{{job-id}}/status` for completion.

## GitHub Actions example

Complete snippet to submit and monitor a job. Adjust values for your environment.

```
YAMLCode



- name: Prepare and Send for Code Fix
				id: send_code_fix
				run: |
					RESPONSE=$(curl --location 'https://api.${{ vars.INSTANCE }}/ai/fix' \
						--header "Authorization: Bearer ${{ env.access_token }}" \
						--form 'RunName="code_reviewer"' \
						--form 'SastName="68c9b94673191c113b8d7ee1"' \
						--form 'jobName="DemoCodeFixer"' \
						--form 'SourceCodeLanguage="Java"' \
						--form 'PromptId="CodeFixer__FixCode_V3"' \
						--form 'TargetExtension="java"' \
						--form 'Llm="${{ vars.LLM }}"' \
						--form 'SearchPattern="*.java"' \
						--form 'JobPreProcessors[]="VCS-CLONE-REPO"' \
						--form 'JobPreProcessors[]="READ-CODE-CONVENTIONS"' \
						--form 'JobPreProcessors[]="VCS-CHECKOUT-REPO"' \
						--form 'ItemContentPostProcessors[]="WRITE-CONTENT"' \
						--form 'ItemContentPostProcessors[]="COPY-CONTENT"' \
						--form 'ItemContentPostProcessors[]="VCS-PULL-REQUEST-FILE-REPO"' \
						--form 'JobPostProcessors[]="VCS-COMMIT-PUSH-REPO"' \
						--form 'JobPostProcessors[]="VCS-PULL-REQUEST-REPO"' \
						--form 'IssueSeverities[]="BLOCKER"' \
						--form 'IssueSeverities[]="CRITICAL"' \
						--form 'IssueSeverities[]="MAJOR"' \
						--form 'SastIssueTypes[]="BUG"' \
						--form 'SastIssueTypes[]="VULNERABILITY"' \
						--form 'SastIssueTypes[]="CODE_SMELL"' \
						--form 'Vcs[0][Name]="Codefixer"' \
						--form 'BranchName="code-fixer"' \
						--form 'BaseBranch="master"' \
						--form 'RepoName="${{ github.repository }}"' \
						--form 'CreatePullRequestPerFile="true"')

					echo "API Response: $RESPONSE"

					# Tip: adjust the field based on your environment's response payload
					# Common examples: .id, .jobId or .job_id
					JOB_ID=$(echo "$RESPONSE" | jq -r '.id // .jobId // .job_id')
					if [ -z "$JOB_ID" ] || [ "$JOB_ID" = "null" ]; then
						echo "Failed to extract job id" >&2
						exit 1
					fi
					echo "job_id=$JOB_ID" >> $GITHUB_ENV

			- name: Monitor Code Fix Job Status
				id: monitor_code_fix_status
				run: |
					JOB_ID=${{ env.job_id }}
					STATUS="Pending"
					while [[ "$STATUS" != "Completed" ]]; do
						RESPONSE=$(curl --location "https://api.${{ vars.INSTANCE }}/ai/jobs/$JOB_ID/status" \
							--header "Authorization: Bearer ${{ env.access_token }}")
						# Some environments use .state instead of .status
						STATUS=$(echo "$RESPONSE" | jq -r '.status // .state // "Pending"')
						echo "Current status: $STATUS"
						if [[ "$STATUS" == "Failed" || "$STATUS" == "Error" ]]; then
							echo "Job failed" >&2
							echo "$RESPONSE"
							exit 1
						fi
						sleep 10
					done

					echo "Final status: $STATUS"
					# Output URIs/artifacts (adjust based on your environment's schema)
					OUTPUT_URIS=$(echo "$RESPONSE" | jq -r '.results[]?.output[]?.uri // empty')
					echo "Output URIs:\n$OUTPUT_URIS"
```

## Optional: list available LLMs

Some installations expose a helper endpoint to list LLMs configured in the backend.

* URL: `POST https://api.<INSTANCE>/bff/llm/list`
* Auth: Bearer token (same access token)
* Use the response to pick the `Llm` id for Code Fixer requests.

Example (curl):

```
TerminalCode



curl --location "https://api.${INSTANCE}/bff/llm/list" \
	--header "Authorization: Bearer ${ACCESS_TOKEN}"
```

## Output and results

* While monitoring, the status endpoint typically returns a JSON with `status`/`state` and `results` containing outputs.
* Use `jq` to extract URIs, messages, or diffs as available in your installation.
* When configured with VCS, the job may create commits and open Pull Requests automatically.

## Optional fields and parameter variations

Names and availability can vary by environment. Common variations and extras:

* `jobName` vs `jobname`: both appear in some installations; prefer `jobName` if supported.
* `JobPreProcessors[]` may also appear as `ItemJobPreProcessors[]` in older configs.
* `ItemContentPreProcessors[]` can include:
  + `ADD-LINE-NUMBERS` (sometimes spelled `ADD-LINE-NUMERS` in legacy setups)
  + `SAST-ANALYSIS({"GenerateFragments":"true","GroupFragmentsByLine":"false"})` (JSON argument supported depending on backend)
* SAST-specific extras (if enabled):
  + `TypeSast`: `SonarQube` or `SonarCloud`
  + `repoProjectId`: provider-specific project id
  + `Conventions`: e.g., `Java`
  + `CsvFile`: path or reference used by custom flows

If a field is not recognized, check your installation’s API schema or remove the line.

## Best practices

* Filter `IssueSeverities[]` and `SastIssueTypes[]` to reduce noise and focus on critical violations.
* For per-file PRs, set `CreatePullRequestPerFile="true"` and ensure predictable branch/naming.
* Store secrets (tokens, passwords) in a secrets manager (e.g., GitHub Secrets).
* For large repositories, use `SearchPattern` to limit scope (e.g., `**/*.java`).

## Troubleshooting

* 401 Unauthorized: check the token and realm/client used for authentication.
* 400 Bad Request: verify required fields and correct processor/prompt names.
* 404 Not Found on status: confirm the `jobId` returned by the job creation.
* Job failed: inspect the full status JSON and returned logs/URIs.

See also:

* Portal configuration for jobs: [Application Settings › Jobs](/settings/application#jobs)

Last modified on February 26, 2026

[Authentication](/api-guides/authentication)[Code Reviewer (API)](/api-guides/code-reviewer)

On this page

* [Overview](/api-guides/code-fixer#overview)
* [Authentication](/api-guides/code-fixer#authentication)
* [Endpoint](/api-guides/code-fixer#endpoint)
  + [Key fields (form-data)](/api-guides/code-fixer#key-fields-form-data)
  + [Content type note](/api-guides/code-fixer#content-type-note)
* [Quick curl example (local)](/api-guides/code-fixer#quick-curl-example-local)
* [Postman quickstart](/api-guides/code-fixer#postman-quickstart)
* [GitHub Actions example](/api-guides/code-fixer#github-actions-example)
* [Optional: list available LLMs](/api-guides/code-fixer#optional-list-available-llms)
* [Output and results](/api-guides/code-fixer#output-and-results)
* [Optional fields and parameter variations](/api-guides/code-fixer#optional-fields-and-parameter-variations)
* [Best practices](/api-guides/code-fixer#best-practices)
* [Troubleshooting](/api-guides/code-fixer#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/wynxx-assist-extension/configuration
Wynxx Assist Extension

# About Wynxx Assist Configuration

Configure the extension to connect with your Wynxx platform instance.

## Auto Ingestion Settings

**Batch Size**

* Number of files per ingestion batch request
* Default: 15 files
* Adjusts processing speed and server load

**Debounce Ms**

* Debounce interval (ms) after file changes before triggering ingestion batch
* Default: 5000 ms
* Prevents excessive ingestion on rapid file changes

**Editor Context Max Chars**

* Maximum number of characters from the editor to include when no selection is made (0 = unlimited)
* Default: 60000 characters
* Controls context size sent to AI models

**Enabled**

* ✓ Enable automatic background ingestion of the workspace into RAG when project config (.wynxx) is present
* Automatically processes workspace files for AI assistance

**Extra Ignore**

* Additional glob ignore patterns besides .wynxxignore
* Default patterns: `**/node_modules/**`, `**/.git/**`, `**/wynxx/**`, `**/dist/**`, `**/out/**`
* Customize which files to exclude from processing

**Include Patterns**

* Glob patterns to include (processed before ignore list)
* Default: `**/*` (includes all files)
* Define which file types to process

**Initial Scan On Startup**

* ✓ Perform a one-time initial full scan of the workspace when extension activates
* Automatically ingests existing files on startup

**Max File Size KB**

* Skip files larger than this size (KB) during auto ingestion
* Default: 512 KB
* Set to 0 for no limit (not recommended)

**Show Status**

* ✓ Show status bar item for auto ingestion queue state
* Displays processing status in VS Code status bar

**Verbose**

* ✓ Verbose logging for auto ingestion (file events, filtering decisions)
* Enable detailed logs for troubleshooting

## Base Configuration

**Base endpoint**

* Inform only the base URL of the instance (without subdomains api/auth/apiroute)
* Example: [https://wynxxexample.com/](https://wynxxexample.com/)
* Your Wynxx platform endpoint

**Language**

* Wynxx Language of answers
* Default: en (English)
* Supported languages for AI responses

## Legacy Transformer

**Supported-languages**

* Wynxx Legacy Transformer Supported Languages
* Default: .cob, .cbl, .jcl, .ts
* File extensions for legacy code transformation

## Required Settings Setup

1. Open VS Code settings (Ctrl/Cmd + ,)
2. Search for "Gft-ai-impact-core" or "Wynxx"
3. Configure the Base endpoint with your Wynxx platform URL
4. Adjust Auto Ingestion settings based on your workspace size and preferences
5. Set Language preference for AI responses
6. Save configuration and restart VS Code

![Wynxx Assist - Configuration Settings](/assets/configure-extension-CwJCf7jr.png)

## Next Steps

After configuration, you can start using the extension's various capabilities for code assistance.

Last modified on February 26, 2026

[About Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist)[About Wynxx Assist Installation](/wynxx-assist-extension/installation)

On this page

* [Auto Ingestion Settings](/wynxx-assist-extension/configuration#auto-ingestion-settings)
* [Base Configuration](/wynxx-assist-extension/configuration#base-configuration)
* [Legacy Transformer](/wynxx-assist-extension/configuration#legacy-transformer)
* [Required Settings Setup](/wynxx-assist-extension/configuration#required-settings-setup)
* [Next Steps](/wynxx-assist-extension/configuration#next-steps)

---


# ORIGEM: https://docs.wynxx.app/feature-benchmark/code-documenter
Feature Benchmark

# Code Documenter

This page explains how to use the Code Documenter Feature Benchmark to evaluate and compare different language models (LLMs) for documentation generation. Follow the steps below to benchmark LLM performance in creating comprehensive code documentation.

## Overview

The Code Documenter Benchmark allows you to evaluate and compare the quality of code documentation generated by different Language Models (LLMs) in a controlled environment. This makes it easier to identify which model produces more complete, clear, and audience-appropriate documentation.

### Purpose and Benefits

**Purpose**: Compare the ability of different AI models to create comprehensive code documentation, tailored to a specific audience (persona) and in a defined output language.

**Benefits**:

* Side-by-side comparison between two models (LLM #1 and LLM #2)
* Documentation adjustment according to target audience (persona)
* Output language selection for different markets and teams
* Consistency in evaluation through controlled parameters
* Practical application for projects requiring code documentation

## Access

* Go to Feature Benchmark → Code Documenter
* Access the benchmark interface to compare LLM performance

## Interface Overview

The Code Documenter Benchmark interface provides:

* **Navigation Bar**: Home breadcrumb, tutorial access, and compare functionality
* **Parameters Section**: Four dropdown selectors (Source Language, Prompt, Persona, Output Language)
* **Hide Source Code**: Expandable section showing code editor with line/character counts
* **LLM Comparison Panels**: Side-by-side results from different models (e.g., AWSBedRock, gpt-4.1)
* **Status Tracking**: Real-time job status and completion indicators for each LLM

![Code Documenter - Complete Interface](/assets/code-documenter-interface-zLK4o3cX.png)

## Step-by-Step Process

### 1. Configure Parameters

Set up the four required parameters in the top parameter bar:

![Code Documenter - Parameters Section](/assets/parameters-section-DAoQSMuO.png)

1. **Source Language**: Select the programming language (e.g., Java, Python, C#, JavaScript)
2. **Prompt**: Choose the documentation prompt (e.g., DocumentCode\_V5)
3. **Persona**: Select target audience from available options (e.g., Software Engineer, Business Analyst, Data Scientist)
4. **Output Language**: Choose documentation language (e.g., English (United States))

### 2. Prepare Source Code

1. **Expand Source Code Section**: Click "Hide source code" to reveal the editor
2. **Edit or Upload Code**: Either edit directly or drag a new file to replace
3. **Verify Code**: Ensure syntax highlighting appears correctly
4. **Check Metrics**: Monitor line and character counts

### 3. Run Documentation Generation

1. **Start Process**: Click the appropriate button to begin documentation generation
2. **Monitor Progress**: Watch the LLM panels for processing status
3. **Wait for Completion**: Both models will process simultaneously

### 4. Review and Compare Results

Each LLM panel displays:

* **Model Identification**: Clear LLM names (e.g., "AWSBedRock (1)", "gpt-4.1 (2)")
* **Processing Status**: "Status: Completed | Job: [tracking-id]"
* **Generated Documentation**: Full documentation with titles and detailed explanations
* **Rich Formatting**: Professional layout with headers and structured content

### Understanding Results

Each result panel displays:

* **LLM Identification**: Clear labeling with model name and instance number
* **Status Information**: Job completion status and tracking IDs
* **Documentation Output**: Full generated documentation with professional formatting
* **Rich Text Formatting**: Proper headers, structure, and readable layout
* **Comparison Tools**: Diff functionality to analyze differences between models

![Code Documenter - Side-by-Side Comparison Results](/assets/comparison-results-BIk39h9J.png)

## Real-World Usage Example

Using the interface with a simple Calculator class demonstrates the comparison capabilities:

**Input Code**:

```
JavaCode



public class Calculator {
    public int sum(int a, int b) {
        return a + b;
    }
    
    public int sub(int a, int b) {
        return a - b;
    }
}
```

**Configuration Used**:

* Source Language: Java
* Prompt: DocumentCode\_V5
* Persona: Software Engineer
* Output Language: English (United States)

**Results Comparison**:

* **AWSBedRock**: "Documenter: Simple Arithmetic Calculator" with concise explanations
* **GPT-4**: "Documenter: Calculator Class" with detailed technical descriptions

## Evaluation Criteria

When comparing the generated documentation, consider:

**Technical Accuracy**:

* Correct interpretation of code functionality
* Accurate description of methods and parameters
* Proper understanding of code structure

**Documentation Quality**:

* Clear and professional writing style
* Appropriate level of detail for the selected persona
* Well-structured organization with headers and sections

**Completeness**:

* Coverage of all important code elements
* Explanation of purpose and usage
* Context-appropriate technical depth

**Persona Alignment**:

* Language and terminology suitable for target audience
* Level of technical detail matching persona requirements
* Focus areas appropriate for the selected role

## Tips

* **Use specific prompts**: Clear instructions yield better documentation quality
* **Choose appropriate personas**: Match persona to your actual target audience
* **Monitor the parameter bar**: Check that all four parameters (Source Language, Prompt, Persona, Output Language) are properly configured
* **Watch status indicators**: Monitor job completion in both LLM panels (shows "Status: Completed | Job: [ID]")
* **Use the Tutorial button**: Access built-in guidance for optimal benchmark usage
* **Test different output languages**: Ensure documentation quality across languages
* **Compare systematically**: Use the Diff button to analyze specific differences between models
* **Consider documentation maintenance**: Evaluate how well documentation could be updated

## Troubleshooting

* **Status not updating**: Refresh the page if LLM panels show stuck processing indicators
* **Poor documentation quality**: Refine prompts with more specific requirements and examples
* **Persona mismatch**: Ensure selected persona aligns with your documentation goals
* **Language issues**: Verify output language selection matches your requirements
* **Source code editor problems**: If the "Hide source code" section doesn't expand properly, try refreshing
* **Parameter configuration**: Ensure all four parameter dropdowns are properly selected before running
* **Model comparison errors**: Ensure both selected LLMs support the chosen programming language
* **Incomplete documentation**: Provide more detailed prompts with specific documentation requirements
* **Job tracking issues**: Use the job IDs shown in status bars to track processing if needed

## Best Practices

* Start with clear, specific prompts that define documentation scope and style
* Select personas that match your actual documentation consumers
* Use consistent evaluation criteria across different documentation types
* Test both models with the same parameters for fair comparison
* Save successful configurations for future documentation projects
* Consider multiple documentation types for comprehensive model evaluation
* Document your evaluation criteria before running benchmarks

Learn more about documentation approaches:

* [Code Documenter Feature Guide](/guides/features/code-documenter)

Last modified on February 26, 2026

[Story Creator 2.0](/guides/features/story-creator2)[Code Tester](/feature-benchmark/code-tester)

On this page

* [Overview](/feature-benchmark/code-documenter#overview)
  + [Purpose and Benefits](/feature-benchmark/code-documenter#purpose-and-benefits)
* [Access](/feature-benchmark/code-documenter#access)
* [Interface Overview](/feature-benchmark/code-documenter#interface-overview)
* [Step-by-Step Process](/feature-benchmark/code-documenter#step-by-step-process)
  + [1. Configure Parameters](/feature-benchmark/code-documenter#1-configure-parameters)
  + [2. Prepare Source Code](/feature-benchmark/code-documenter#2-prepare-source-code)
  + [3. Run Documentation Generation](/feature-benchmark/code-documenter#3-run-documentation-generation)
  + [4. Review and Compare Results](/feature-benchmark/code-documenter#4-review-and-compare-results)
  + [Understanding Results](/feature-benchmark/code-documenter#understanding-results)
* [Real-World Usage Example](/feature-benchmark/code-documenter#real-world-usage-example)
* [Evaluation Criteria](/feature-benchmark/code-documenter#evaluation-criteria)
* [Tips](/feature-benchmark/code-documenter#tips)
* [Troubleshooting](/feature-benchmark/code-documenter#troubleshooting)
* [Best Practices](/feature-benchmark/code-documenter#best-practices)

---


# ORIGEM: https://docs.wynxx.app/api-guides/postman-quickstart
API (How-To)

# Postman Quickstart

Use this page for steps that are common across API guides when using Postman: creating an environment, obtaining an access token, and reusing variables across requests.

## Create a Postman environment

1. In Postman, click Environments → + (New)
2. Name it something like AI Impact (dev/stage/prod)
3. Add variables (Initial Value = Current Value):
   * `server`: `https://api.<INSTANCE>`
   * `auth_server`: `https://auth.<INSTANCE>`
   * Optional: `DefaultLLM`, `job-id`, etc.
4. Save, and select this environment from the top-right environment picker

## Obtain an access token (Token request)

Create a request named “Token” to get an `access_token` from Keycloak.

* Method: `POST`
* URL: `{{auth_server}}/realms/ai-impact/protocol/openid-connect/token`
* Headers: `Content-Type: application/x-www-form-urlencoded`
* Body (x-www-form-urlencoded):
  + `client_id=ai-impact-client`
  + `username=...` and `password=...`
  + `grant_type=password`

Optional (Tests tab): store the token for reuse

```
JavascriptCode



var token = pm.response.json();
pm.globals.set("token", token.access_token);
```

You can now reference the token in subsequent requests using `{{token}}`.

## Reusing variables in requests

* Use `{{server}}` and `{{auth_server}}` in URLs
* Use `{{token}}` in Authorization → Bearer Token or in headers
* Set additional variables (e.g., `job-id`) via test scripts or manually

## Notes

* Some endpoints prefer `multipart/form-data`; others accept `application/x-www-form-urlencoded` for quick testing. Check each API guide for the recommended content type.
* If you receive 401/403, the token may have expired; rerun the Token request.

Last modified on February 26, 2026

[Code Reviewer (API)](/api-guides/code-reviewer)[About Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist)

On this page

* [Create a Postman environment](/api-guides/postman-quickstart#create-a-postman-environment)
* [Obtain an access token (Token request)](/api-guides/postman-quickstart#obtain-an-access-token-token-request)
* [Reusing variables in requests](/api-guides/postman-quickstart#reusing-variables-in-requests)
* [Notes](/api-guides/postman-quickstart#notes)

---


# ORIGEM: https://docs.wynxx.app/legacy-transformer-web/getting-started
Legacy Transformer Web

# Getting Started

Transform your legacy applications into modern, maintainable, and scalable systems through automated analysis, domain classification, business rule extraction, and code generation.

![Legacy Transformer Overview](/assets/legacy-transformer-overview-CVBYrzwm.png)

## Step 1: Create Archetypes

Before creating projects, define archetypes for code generation. Archetypes are templates that determine how your modernized code will be structured.

### Creating an Archetype

1. Go to **Legacy Transformer** → **Archetypes**
2. Click **"New Archetype"**
3. Complete the required fields
4. Save with **"New Archetype"** button
5. Verify it appears in the archetype list

![Archetypes](/assets/archetype-overview-BpYo8tQ1.png)

**Note**: You can create multiple archetypes for different code sources. Edit or delete archetypes using the three-dot menu. Archetypes are essential for Phase C (Code Generation).

## Step 2: Create a Project

With your archetype ready, create a new modernization project.

### Project Creation

1. Go to **Legacy Transformer** → **Projects**
2. Click **"Create Project"**
3. Fill in the details:
   * **Project Name**: Descriptive name for your project
   * **Repository URL**: Git repository location (e.g., `https://github.com/user/repo`)
   * **Branch**: Source branch to analyze (usually `main`)
   * **Version Control System**: Select your VCS type

![Create Project Form](/assets/create-project-DPtBDc3k.png)

4. Save the new project
5. The system lists the project as **"NEW"** on the projects screen

![Projects List](/assets/projects-list-Cvvz-YAU.png)

**Now you're ready to start the analysis. Click the "View" button!**

## Step 3: Project Overview

After creating your project, review the project details before starting analysis.

![Project Overview](/assets/project-overview-DNZgj2KE.png)

The overview shows:

* **Project ID**: Unique identifier
* **Status**: Current project status (NEW, IN PROGRESS, COMPLETED)
* **Repository URL**: Source code location
* **Branch**: Target branch for analysis
* **Created By**: Project creator
* **Created At**: Project creation date

## Step 4: Code Analysis (Phase A)

Perform automated analysis of your legacy codebase. This represents **Phase A: Scan & Analysis** where the system accesses your repository and breaks down the entire file structure and logic.

### Starting Analysis

1. Click on **"Code Analysis"** tab
2. Configure the analysis:
   * **Select an LLM** from the list
   * **Choose analysis types**: Basic Metrics, Dependencies, or Domains
   * **File Extensions**: Enter file types (e.g., ".java")
   * **Excluded Patterns**: Add patterns to exclude (can modify defaults)
3. Click **"Start Analysis"**

The system will perform a complete "X-ray" of your current code.

### Analysis Results

When analysis completes, review these sections:

#### Overview

* Total files analyzed
* Lines of code count
* Programming languages detected
* Code composition breakdown
* Number of commented lines
* Number of blank lines
* Total tokens consumed
* Analysis completion status

#### Code Metrics

Displays comprehensive code quality measurements:

**Code Density**

```
Code



Code Density = (Code Lines / Total Lines) × 100
```

* **Code Lines**: Lines containing instructions, declarations, or executable logic
* **Total Lines**: All lines including whitespace and comments
* **Tip**: 100% density isn't always good—may indicate lack of documentation

**Documentation (Comments)**

```
Code



Comments = (Comment Lines / Total Lines) × 100
```

Shows percentage of code dedicated to explanations and comments.

#### Languages

* File count per programming language
* Lines of code per language
* Token consumption per language
* Language distribution percentages

#### Dependencies

* **Internal Dependencies**: Project-internal references
* **External Dependencies**: Third-party libraries
* **Missing Dependencies**: Unresolved references
* **Unreferenced Files**: Unused files
* **Circular Dependencies**: Problematic circular references

#### Domains

* **Business Domains**: Core business logic areas
* **Infrastructure Domains**: Technical infrastructure components
* **File Classifications**: Files categorized by domain type

#### Dependency Graph

Visual representation showing:

* Total files as nodes
* Total dependencies as connections
* Unreferenced files highlighted

## Step 5: Inventory Management

Review and manage identified domains and business rules.

### Using Inventory

1. Click on **"Inventory"** section
2. Review the list of created Domains and Business Rules
3. Select a domain or rule and mark it as reviewed
4. View the list of files by domain or by rule
5. Move files to other domains as needed

### Domain Management

* Search for specific domains
* Add new domains manually
* Reorganize file classifications
* Validate domain accuracy

## Step 6: Business Rules Generation (Phase B)

Generate documentation with business rules for each selected domain and its files.

### Generating Business Rules

1. Click on **"Business Rules"** section
2. Select a domain
3. Select an LLM model
4. Click **"Generate Business Rule"**

The system processes rules for each related file and generates corresponding documentation.

### Managing Generated Rules

* **Mark as reviewed**: Validate the generated rules
* **Edit document**: Modify rules as needed
* **Save document**: Preserve your changes
* **Revert to original**: Undo modifications

## Step 7: Code Generation (Phase C)

Generate modern backend and frontend code for each business rule defined per domain.

### Backend Code Generation

1. Click on **"Code Generation"** option
2. Select the business rule
3. Select the archetype for backend
4. Select the LLM model type
5. Wait for backend code generation to complete

### Frontend Code Generation

After backend completion:

1. Continue with frontend code generation
2. Use appropriate frontend archetype
3. Wait for completion

### Download and Review

* Download the generated code
* Review in your preferred editor
* Validate the modernized implementation
* Test the generated code

## Best Practices

**Before Starting:**

* Ensure repository access is properly configured
* Create comprehensive archetypes for your target architecture
* Choose appropriate file extensions for analysis

**During Analysis:**

* Monitor progress but avoid interrupting the process
* Review all analysis results thoroughly
* Validate domain classifications

**After Generation:**

* Test generated code thoroughly
* Review business rules for accuracy
* Document any manual adjustments needed

## Troubleshooting

**Common Issues:**

* **Repository access**: Verify authentication and permissions
* **Long analysis time**: Large repositories require more processing time
* **Missing dependencies**: Check file paths and external libraries
* **Generation errors**: Verify archetype configuration and business rules

---

Ready to transform your legacy application? Start by creating your first archetype and project!

Last modified on February 26, 2026

[Overview](/legacy-transformer-web/overview)[Overview](/api-guides)

On this page

* [Step 1: Create Archetypes](/legacy-transformer-web/getting-started#step-1-create-archetypes)
  + [Creating an Archetype](/legacy-transformer-web/getting-started#creating-an-archetype)
* [Step 2: Create a Project](/legacy-transformer-web/getting-started#step-2-create-a-project)
  + [Project Creation](/legacy-transformer-web/getting-started#project-creation)
* [Step 3: Project Overview](/legacy-transformer-web/getting-started#step-3-project-overview)
* [Step 4: Code Analysis (Phase A)](/legacy-transformer-web/getting-started#step-4-code-analysis-phase-a)
  + [Starting Analysis](/legacy-transformer-web/getting-started#starting-analysis)
  + [Analysis Results](/legacy-transformer-web/getting-started#analysis-results)
* [Step 5: Inventory Management](/legacy-transformer-web/getting-started#step-5-inventory-management)
  + [Using Inventory](/legacy-transformer-web/getting-started#using-inventory)
  + [Domain Management](/legacy-transformer-web/getting-started#domain-management)
* [Step 6: Business Rules Generation (Phase B)](/legacy-transformer-web/getting-started#step-6-business-rules-generation-phase-b)
  + [Generating Business Rules](/legacy-transformer-web/getting-started#generating-business-rules)
  + [Managing Generated Rules](/legacy-transformer-web/getting-started#managing-generated-rules)
* [Step 7: Code Generation (Phase C)](/legacy-transformer-web/getting-started#step-7-code-generation-phase-c)
  + [Backend Code Generation](/legacy-transformer-web/getting-started#backend-code-generation)
  + [Frontend Code Generation](/legacy-transformer-web/getting-started#frontend-code-generation)
  + [Download and Review](/legacy-transformer-web/getting-started#download-and-review)
* [Best Practices](/legacy-transformer-web/getting-started#best-practices)
* [Troubleshooting](/legacy-transformer-web/getting-started#troubleshooting)

---


# ORIGEM: https://docs.wynxx.app/installation/override-values
Installation

# Configure Override-Values.yaml

Use this file to customize your deployment. The installer script reads it and passes the values to the Helm chart. This page explains each field, how to set your domain names, storage classes, and where secrets should live.

Note: Keep secrets out of source control. Prefer Kubernetes Secrets or a secret manager. If you must keep a working copy locally, ensure the file is excluded from git.

Tip: If you’re deploying to a public cloud, also see cloud-specific prerequisites in Installation → Cloud ([Azure](/installation/cloud/azure), [AWS](/installation/cloud/aws), [GCP](/installation/cloud/gcp)).

## Quick start: replace placeholders

If you received a template similar to this:

* `__client_name__`: your organization/client identifier (for display and tagging).
* `__instance__`: your tenant name (also used as the Kubernetes namespace), for example `tenant-a`.
* `__domain__`: the apex DNS name for the tenant, for example `tenant-a.example.com`.

Then set hosts as follows (assuming `frontendIngress = tenant-a.example.com`):

* `backendIngress = api.tenant-a.example.com`
* `backendFtIngress = apift.tenant-a.example.com`
* `keycloakIngress = auth.tenant-a.example.com`
* `frontendIngress = tenant-a.example.com` (no subdomain)
* `redisIngress = redis.tenant-a.example.com`
* `zipkinIngress = zipkin.tenant-a.example.com`

The bundled Ingress definitions are set to issue a wildcard TLS certificate for `*.tenant-a.example.com` (via cert-manager) and to expose the frontend at `tenant-a.example.com`.

## Field-by-field guide

### global

* `client`: a label used across the stack (UI and metadata).
* `cloud`: where you’re deploying (azure, aws, gcp, others). Used for defaults and documentation cues.
* `tenant`: tenant/namespace name in Kubernetes. The installer uses this to scope resources. Unique per environment.
* `networkAccessOption`: how services are exposed.
  + `ingress` (recommended): traffic enters via ingress controller. Requires an ingress controller and DNS records.
  + `loadBalancer`: expose Services directly with type LoadBalancer. Optionally set static IPs under `serviceExtras.spec`.
* `endpoints`: the public hostnames.
  + `frontendIngress`: apex host, e.g., `tenant-a.example.com`.
  + `backendIngress`: API host, e.g., `api.tenant-a.example.com`.
  + `backendFtIngress`: Functional tester API host, e.g., `apift.tenant-a.example.com`.
  + `keycloakIngress`: IdP host, e.g., `auth.tenant-a.example.com`.
  + `redisIngress`: Optional Redis UI/API host (if exposed), e.g., `redis.tenant-a.example.com`.
  + `zipkinIngress`: Tracing UI, e.g., `zipkin.tenant-a.example.com`.

### global.storage (cloud-specific notes for Azure)

* `storageClass`: default StorageClass for app data. On Azure, prefer `azurefile-csi` for ReadWriteMany (RWX) on modern clusters; if your cluster still uses legacy classes, `azurefile` works. For ReadWriteOnce (RWO), prefer `managed-csi`; legacy clusters may use `managed`.
* `storageClassMongo`: must be a block (RWO) StorageClass; on Azure, use `managed-csi` (or `managed` on older clusters). MongoDB cannot use Azure File.
* `storageClassPostgress` and `storageClassKeycloak`: similar to Mongo—use your cloud’s default RWO class (on Azure, `managed-csi` or legacy `managed`).
* `storageName`: logical name for the storage layer; used by the chart to label or map volumes.

Tips:

* Verify classes with `kubectl get storageclass` and match names exactly.
* If your cluster doesn’t have RWX, keep components on RWO and scale accordingly.

### global.acr (image registry credentials)

* `username`, `password`: registry credentials (Azure Container Registry in this example). Security tips:
  + Do not commit these credentials to git. Prefer creating a Kubernetes Secret (imagePullSecret) and reference it in the chart if supported.
  + Rotate credentials regularly.

### serviceExtras

Optional knobs for Services and annotations. Useful when `networkAccessOption = loadBalancer`.

* `annotations`: attach provider-specific annotations (e.g., for AWS/NLB, GCP, Azure).
* `spec.backendLoadBalancerIP` (and friends): assign static IPs to LoadBalancer Services. Ensure those IPs exist and are reserved in your cloud provider.

### certificate

* `enabled: true` assumes cert-manager is installed and a ClusterIssuer named `letsencrypt` exists.
* If you don’t use cert-manager, set `enabled: false` and manage TLS secrets manually, or change the annotations/issuer name to match your setup.

### litellm and backendCompi

Feature toggles. Set to `true` only if you plan to enable and configure those components.

### ingresses

The file defines five ingress resources: backend, backend functional tester, frontend, keycloak, and redis. Key points:

* `ingressClassName`: must match your ingress controller (e.g., `nginx`).
* Annotations configure timeouts, body sizes, sticky sessions for Keycloak, and cert-manager issuer.
* TLS uses a wildcard certificate for `*.frontendIngress` and a dedicated secret `tenant-certificate-secret`.
  + Ensure your DNS has A/AAAA records pointing all hosts (`frontendIngress` and subdomains) to the ingress controller.
  + Ensure the ClusterIssuer exists and is allowed to request certificates for your domain.

Service ports (by default in the sample chart):

* Backend: 443
* Backend Functional Tester: 443
* Frontend: 443
* Keycloak: 8080 (service), exposed via HTTPS by ingress
* Redis: 6379

### litellmProxy.secrets

Do not ship real credentials in this file. Prefer one of:

* Create a Kubernetes Secret and reference it via values (if the chart supports `existingSecret`).
* Use an external secret manager (e.g., Azure Key Vault) and sync into Kubernetes.
* If temporarily placing here for a test environment, rotate keys immediately after.

## Minimal example (Azure, Ingress, wildcard TLS)

Replace values in angle brackets with your own:

```
YAMLCode



global:
  client: <your-client-name>
  cloud: azure
  tenant: <tenant-namespace>
  networkAccessOption: ingress
  endpoints:
    frontendIngress: <tenant.example.com>
    backendIngress: api.<tenant.example.com>
    backendFtIngress: apift.<tenant.example.com>
    keycloakIngress: auth.<tenant.example.com>
    redisIngress: redis.<tenant.example.com>
    zipkinIngress: zipkin.<tenant.example.com>
  storage:
    storageClass: azurefile
    storageClassMongo: managed
    storageClassPostgress: managed
    storageClassKeycloak: managed
    storageName: ai-impact-storage
  acr:
    username: <acr-username>   # Prefer using an imagePullSecret instead
    password: <acr-password>

serviceExtras:
  annotations: {}
  # spec:
  #   backendLoadBalancerIP: 10.0.0.10

certificate:
  enabled: true  # requires cert-manager + ClusterIssuer "letsencrypt"

litellm:
  enabled: false

backendCompi:
  enabled: false

ingresses:
  - name: ai-impact-ingress-backend
    ingressClassName: "nginx"
    namespace: "{{ .Values.global.tenant }}"
    annotations:
      nginx.ingress.kubernetes.io/force-ssl-redirect: "true"
      cert-manager.io/cluster-issuer: "letsencrypt"
    tls:
      - hosts:
          - "*.{{ .Values.global.endpoints.frontendIngress }}"
        secretName: tenant-certificate-secret
    rules:
      - host: "{{ .Values.global.endpoints.backendIngress }}"
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: ai-impact-backend-service
                port: 443
  # ...repeat for other ingresses as needed (frontend, keycloak, etc.)
```

## Validation checklist

* DNS created for: `frontendIngress` and subdomains (api, apift, auth, redis, zipkin).
* cert-manager installed and ClusterIssuer name matches your values.
* StorageClasses exist and match names in this file.
* For LoadBalancer mode, static IPs are reserved and set under `serviceExtras.spec`.
* Secrets are not committed; use Kubernetes Secrets or an external secret manager.

Once configured, proceed to the Step-by-step page to run the installer with your finalized `override-values.yaml`.

## Cloud-specific examples and annotations

Below are minimal examples and commonly used annotations for each provider. Use these as guidance and adapt to your cluster’s conventions.

### Azure (AKS)

* Recommended StorageClasses:
  + RWO: `managed-csi` (or `managed` on older AKS)
  + RWX: `azurefile-csi` (or `azurefile` on older AKS)
* Ingress: NGINX Ingress Controller (ingressClassName: `nginx`) + cert-manager with ClusterIssuer `letsencrypt`.
* Optional LoadBalancer (when not using ingress) annotations: set static IPs or make internal.

Minimal values:

```
YAMLCode



global:
  cloud: azure
  tenant: <tenant-namespace>
  networkAccessOption: ingress
  endpoints:
    frontendIngress: <tenant.example.com>
    backendIngress: api.<tenant.example.com>
    backendFtIngress: apift.<tenant.example.com>
    keycloakIngress: auth.<tenant.example.com>
  storage:
    storageClass: azurefile-csi
    storageClassMongo: managed-csi
    storageClassPostgress: managed-csi
    storageClassKeycloak: managed-csi

ingresses:
  - name: ai-impact-ingress-frontend
    ingressClassName: "nginx"
    annotations:
      nginx.ingress.kubernetes.io/force-ssl-redirect: "true"
      cert-manager.io/cluster-issuer: "letsencrypt"
    tls:
      - hosts:
          - "*.{{ .Values.global.endpoints.frontendIngress }}"
        secretName: tenant-certificate-secret
    rules:
      - host: "{{ .Values.global.endpoints.frontendIngress }}"
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: ai-impact-frontend-service
                port: 443

# Only when using LoadBalancer services (networkAccessOption: loadBalancer):
serviceExtras:
  annotations:
    service.beta.kubernetes.io/azure-load-balancer-internal: "true"   # optional: internal LB
    # service.beta.kubernetes.io/azure-load-balancer-resource-group: <rg-name>  # for cross-RG IPs
  # spec:
  #   backendLoadBalancerIP: 10.0.0.10
```

### AWS (EKS)

* Recommended StorageClasses:
  + RWO: `gp3` (or `gp2` on older clusters) via EBS CSI
  + RWX: `efs-sc` (via EFS CSI driver)
* Ingress options:
  + NGINX Ingress Controller (ingressClassName: `nginx`), or
  + AWS Load Balancer Controller (ALB) with `ingressClassName: alb` and ALB annotations.

Minimal values with NGINX ingress:

```
YAMLCode



global:
  cloud: aws
  tenant: <tenant-namespace>
  networkAccessOption: ingress
  endpoints:
    frontendIngress: <tenant.example.com>
    backendIngress: api.<tenant.example.com>
    keycloakIngress: auth.<tenant.example.com>
  storage:
    storageClass: efs-sc
    storageClassMongo: gp3
    storageClassPostgress: gp3
    storageClassKeycloak: gp3

ingresses:
  - name: ai-impact-ingress-backend
    ingressClassName: "nginx"
    annotations:
      nginx.ingress.kubernetes.io/force-ssl-redirect: "true"
      cert-manager.io/cluster-issuer: "letsencrypt"
    tls:
      - hosts:
          - "*.{{ .Values.global.endpoints.frontendIngress }}"
        secretName: tenant-certificate-secret
    rules:
      - host: "{{ .Values.global.endpoints.backendIngress }}"
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: ai-impact-backend-service
                port: 443

# If you prefer ALB instead of NGINX:
# ingresses:
#   - name: ai-impact-ingress-backend
#     ingressClassName: "alb"
#     annotations:
#       alb.ingress.kubernetes.io/scheme: internet-facing
#       alb.ingress.kubernetes.io/target-type: ip
#       alb.ingress.kubernetes.io/listen-ports: '[{"HTTPS":443}]'
#       cert-manager.io/cluster-issuer: "letsencrypt"

# Only when using LoadBalancer services (networkAccessOption: loadBalancer):
serviceExtras:
  annotations:
    service.beta.kubernetes.io/aws-load-balancer-type: "nlb"
    service.beta.kubernetes.io/aws-load-balancer-scheme: internet-facing
    service.beta.kubernetes.io/aws-load-balancer-cross-zone-load-balancing-enabled: "true"
```

### GCP (GKE)

* Recommended StorageClasses:
  + RWO: `standard-rwo` (or `premium-rwo` if using SSD)
  + RWX: `filestore-csi` (requires Filestore CSI driver and instance)
* Ingress options:
  + NGINX Ingress Controller (ingressClassName: `nginx`), or
  + GCE/GKE managed ingress (ingressClassName: `gce` or `gce-internal`) with appropriate annotations.

Minimal values with NGINX ingress:

```
YAMLCode



global:
  cloud: gcp
  tenant: <tenant-namespace>
  networkAccessOption: ingress
  endpoints:
    frontendIngress: <tenant.example.com>
    backendIngress: api.<tenant.example.com>
    keycloakIngress: auth.<tenant.example.com>
  storage:
    storageClass: filestore-csi
    storageClassMongo: standard-rwo
    storageClassPostgress: standard-rwo
    storageClassKeycloak: standard-rwo

ingresses:
  - name: ai-impact-ingress-frontend
    ingressClassName: "nginx"
    annotations:
      nginx.ingress.kubernetes.io/force-ssl-redirect: "true"
      cert-manager.io/cluster-issuer: "letsencrypt"
    tls:
      - hosts:
          - "*.{{ .Values.global.endpoints.frontendIngress }}"
        secretName: tenant-certificate-secret
    rules:
      - host: "{{ .Values.global.endpoints.frontendIngress }}"
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: ai-impact-frontend-service
                port: 443

# Only when using LoadBalancer services (networkAccessOption: loadBalancer):
serviceExtras:
  annotations:
    cloud.google.com/load-balancer-type: "External"   # or "Internal" for ILB
    networking.gke.io/load-balancer-type: "External"
    # networking.gke.io/v1beta1.FrontendConfig: <frontend-config-name>  # if using custom SSL policies
```

Notes:

* Replace service names and ports if your chart differs from the defaults.
* Keep `cert-manager.io/cluster-issuer` in sync with your issuer name.
* For RWX on AWS and GCP, ensure the CSI drivers (EFS/Filestore) are installed and backing resources are provisioned.

Last modified on February 26, 2026

[Installation Wizard Guide](/installation/installer-wizard)[Helm CLI Installation](/installation/steps)

On this page

* [Quick start: replace placeholders](/installation/override-values#quick-start-replace-placeholders)
* [Field-by-field guide](/installation/override-values#field-by-field-guide)
  + [global](/installation/override-values#global)
  + [global.storage (cloud-specific notes for Azure)](/installation/override-values#globalstorage-cloud-specific-notes-for-azure)
  + [global.acr (image registry credentials)](/installation/override-values#globalacr-image-registry-credentials)
  + [serviceExtras](/installation/override-values#serviceextras)
  + [certificate](/installation/override-values#certificate)
  + [litellm and backendCompi](/installation/override-values#litellm-and-backendcompi)
  + [ingresses](/installation/override-values#ingresses)
  + [litellmProxy.secrets](/installation/override-values#litellmproxysecrets)
* [Minimal example (Azure, Ingress, wildcard TLS)](/installation/override-values#minimal-example-azure-ingress-wildcard-tls)
* [Validation checklist](/installation/override-values#validation-checklist)
* [Cloud-specific examples and annotations](/installation/override-values#cloud-specific-examples-and-annotations)
  + [Azure (AKS)](/installation/override-values#azure-aks)
  + [AWS (EKS)](/installation/override-values#aws-eks)
  + [GCP (GKE)](/installation/override-values#gcp-gke)

---


# ORIGEM: https://docs.wynxx.app/installation/cloud/aws
Installation

# AWS EKS Prerequisites

This page provides complete prerequisites and setup instructions for deploying Wynxx on Amazon Web Services using Elastic Kubernetes Service (EKS).

## Required Tools

| Tool | Version | Installation | Verification |
| --- | --- | --- | --- |
| **AWS CLI** | 2.0+ | [Install Guide](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html) | `aws --version` |
| **eksctl** | 0.150+ | [eksctl.io](https://eksctl.io/) | `eksctl version` |
| **kubectl** | 1.24+ | [kubernetes.io](https://kubernetes.io/docs/tasks/tools/) | `kubectl version --client` |
| **helm** | 3.12+ | [helm.sh](https://helm.sh/docs/intro/install/) | `helm version` |

### AWS CLI Installation

```
TerminalCode



# macOS
brew install awscli

# Linux
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

# Windows
msiexec.exe /i https://awscli.amazonaws.com/AWSCLIV2.msi
```

### eksctl Installation

```
TerminalCode



# macOS
brew tap weaveworks/tap
brew install weaveworks/tap/eksctl

# Linux
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

---

## AWS Authentication

```
TerminalCode



# Configure credentials (Access Keys)
aws configure

# Or use existing profile
export AWS_PROFILE=my-profile

# Verify
aws sts get-caller-identity
```

---

## Required IAM Permissions

| Policy | Purpose |
| --- | --- |
| `AmazonEKSClusterPolicy` | EKS cluster management |
| `AmazonEKSWorkerNodePolicy` | Node group management |
| `AmazonEKS_CNI_Policy` | VPC CNI networking |
| `AmazonEC2ContainerRegistryReadOnly` | Pull container images |
| `AmazonEBSCSIDriverPolicy` | EBS volume provisioning |
| `AmazonEFSCSIDriverPolicy` | EFS volume provisioning |

---

## Assumptions

* An AWS account is available for deployment (customer-managed or GFT-managed).
* If installed in the customer's AWS, network connectivity between GFT and the customer environment is established as needed.
* LLM providers (Amazon Bedrock, Azure OpenAI, OpenAI, Google Vertex) are active with endpoints and API keys available.
* The pre-installation checklist is completed and validated by the GFT deployment team.
* If pulling images from GFT registry, outbound access to `gftai.azurecr.io:443` and `gftai.westeurope.data.azurecr.io:443` is allowed.

---

## Required Accesses and Permissions

* AWS permissions sufficient to create or administer:
  + Amazon EKS (or your chosen Kubernetes target)
  + Amazon ECR (if using customer registry)
  + Networking (VPCs, Subnets, Internet/NAT Gateways, Load Balancers, Route53 if applicable)
  + Secrets Manager/Parameter Store (optional but recommended)
  + CloudWatch (optional for observability)
* Access to the source code repository and CI/CD with read/write permissions.
* Access to a generative LLM provider with endpoint and token; know your model name(s).
* Access to SAST tools with endpoint and token; read-only permissions are sufficient.

---

## EKS Cluster Creation

```
TerminalCode



# Variables
CLUSTER_NAME="wynxx-cluster"
REGION="us-east-1"

# Create cluster with eksctl
eksctl create cluster \
  --name $CLUSTER_NAME \
  --region $REGION \
  --version 1.29 \
  --nodegroup-name standard-workers \
  --node-type m5.xlarge \
  --nodes 3 \
  --nodes-min 2 \
  --nodes-max 5 \
  --managed

# Get credentials
aws eks update-kubeconfig --name $CLUSTER_NAME --region $REGION

# Verify
kubectl get nodes
```

---

## EKS Core Addons

These addons must be installed and ACTIVE:

```
TerminalCode



# Check addon status
aws eks describe-addon --cluster-name $CLUSTER_NAME --addon-name vpc-cni --region $REGION
aws eks describe-addon --cluster-name $CLUSTER_NAME --addon-name coredns --region $REGION
aws eks describe-addon --cluster-name $CLUSTER_NAME --addon-name kube-proxy --region $REGION

# Install EBS CSI Driver
eksctl create iamserviceaccount \
  --name ebs-csi-controller-sa \
  --namespace kube-system \
  --cluster $CLUSTER_NAME \
  --region $REGION \
  --attach-policy-arn arn:aws:iam::aws:policy/service-role/AmazonEBSCSIDriverPolicy \
  --approve

aws eks create-addon \
  --cluster-name $CLUSTER_NAME \
  --addon-name aws-ebs-csi-driver \
  --region $REGION

# Install EFS CSI Driver
eksctl create iamserviceaccount \
  --name efs-csi-controller-sa \
  --namespace kube-system \
  --cluster $CLUSTER_NAME \
  --region $REGION \
  --attach-policy-arn arn:aws:iam::aws:policy/service-role/AmazonEFSCSIDriverPolicy \
  --approve

aws eks create-addon \
  --cluster-name $CLUSTER_NAME \
  --addon-name aws-efs-csi-driver \
  --region $REGION
```

---

## AWS-Specific Storage Classes

### EBS gp3 (RWO) - Database Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: gp3
provisioner: ebs.csi.aws.com
parameters:
  type: gp3
  fsType: ext4
volumeBindingMode: WaitForFirstConsumer
allowVolumeExpansion: true
reclaimPolicy: Retain
```

### EFS (RWX) - Shared Storage

```
YAMLCode



apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: efs-sc
provisioner: efs.csi.aws.com
parameters:
  provisioningMode: efs-ap
  fileSystemId: fs-XXXXXXXX  # Replace with your EFS ID
  directoryPerms: "777"
volumeBindingMode: Immediate
reclaimPolicy: Retain
```

---

## EFS File System Creation

```
TerminalCode



# Create EFS file system
EFS_ID=$(aws efs create-file-system \
  --performance-mode generalPurpose \
  --throughput-mode bursting \
  --encrypted \
  --region $REGION \
  --tags Key=Name,Value=wynxx-efs \
  --query 'FileSystemId' --output text)

echo "EFS ID: $EFS_ID"

# Get VPC and Subnets from EKS
VPC_ID=$(aws eks describe-cluster --name $CLUSTER_NAME --region $REGION \
  --query 'cluster.resourcesVpcConfig.vpcId' --output text)

SUBNET_IDS=$(aws eks describe-cluster --name $CLUSTER_NAME --region $REGION \
  --query 'cluster.resourcesVpcConfig.subnetIds' --output text)

# Create mount targets (for each subnet)
for SUBNET in $SUBNET_IDS; do
  aws efs create-mount-target \
    --file-system-id $EFS_ID \
    --subnet-id $SUBNET \
    --region $REGION
done
```

---

## DNS Configuration (Route53)

```
TerminalCode



# Get the Ingress LoadBalancer hostname
INGRESS_HOST=$(kubectl get svc -n ingress-nginx ingress-nginx-controller \
  -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')

HOSTED_ZONE_ID="YOUR_HOSTED_ZONE_ID"
DOMAIN="example.com"
PREFIX="wynxx"

# Create wildcard CNAME (recommended for AWS)
aws route53 change-resource-record-sets --hosted-zone-id $HOSTED_ZONE_ID \
  --change-batch '{
    "Changes": [{
      "Action": "CREATE",
      "ResourceRecordSet": {
        "Name": "*.'$PREFIX'.'$DOMAIN'",
        "Type": "CNAME",
        "TTL": 300,
        "ResourceRecords": [{"Value": "'$INGRESS_HOST'"}]
      }
    }]
  }'

# Plus the base domain
aws route53 change-resource-record-sets --hosted-zone-id $HOSTED_ZONE_ID \
  --change-batch '{
    "Changes": [{
      "Action": "CREATE",
      "ResourceRecordSet": {
        "Name": "'$PREFIX'.'$DOMAIN'",
        "Type": "CNAME",
        "TTL": 300,
        "ResourceRecords": [{"Value": "'$INGRESS_HOST'"}]
      }
    }]
  }'
```

---

## Container Registry

* **Option A**: Pull directly from GFT ACR (allow outbound to endpoints listed above).
* **Option B**: Mirror images to your own Amazon ECR and configure credentials in Helm overrides.

---

## Authentication and Identity

* Keycloak is the reference identity provider used by Wynxx.
  + Prepare a realm (or use provided realm export) and know Issuer URL and client IDs.
  + Alternatively, integrate your corporate IdP via Keycloak (OIDC/SAML).

---

## Integrations (Endpoints and Tokens)

* **Agile tools** (e.g., GitHub, GitLab, Jira, Azure DevOps): base URL and API token with required scopes.
* **SAST tools** (e.g., SonarQube, SonarCloud, Fortify): base URL and read-only API token.

---

## VS Code Extensions

* Wynxx extensions (Code Dialoguer, Documenter, Tester, Legacy Transformer) are delivered as VS Code extensions.
* Ensure Wynxx endpoints are reachable from developer networks.
* If RAG is in scope, provision and configure the RAG backend accordingly.

---

## Network and Security

* Decide on public vs private exposure:
  + Public ALB/NLB with TLS and WAF
  + Private endpoints with private hosted zones and VPC-only access
* Open required egress to LLM providers and any external systems you will integrate.

---

## What to Prepare Before Running the Installer

* kubeconfig targeting your EKS cluster
* DNS names and TLS certificates (ACM or your PKI) for portal and API
* A container registry and pull credentials strategy (GFT ACR or your ECR)
* LLM provider credentials and model names
* SAST/Agile integration endpoints and tokens
* Keycloak issuer URL and client IDs (or a plan to manage Keycloak on the cluster)
* The Subscription.zip and override-values.yaml adjusted for your environment

---

## AWS EKS Checklist

* AWS CLI installed (`aws --version`)
* eksctl installed (`eksctl version`)
* AWS credentials configured
* EKS Cluster created (3+ nodes, m5.xlarge+)
* Cluster credentials obtained (`aws eks update-kubeconfig`)
* VPC CNI addon active
* CoreDNS addon active
* kube-proxy addon active
* EBS CSI Driver installed
* EFS CSI Driver installed
* EFS File System created with mount targets
* StorageClass `gp3` created
* StorageClass `efs-sc` created
* NGINX Ingress installed
* Cert-Manager installed
* ClusterIssuer configured
* DNS records configured (Route53 or Cloudflare)
* Subscription.zip available
* ACR credentials available

---

## See Also

* [Prerequisites](/installation/prerequisites)
* [Installation Overview](/installation)
* [Step-by-step (Helm)](/installation/steps)
* [Validation](/installation/validation)

Last modified on February 26, 2026

[Cloud-Specific Prerequisites](/installation/cloud)[Azure AKS Prerequisites](/installation/cloud/azure)

On this page

* [Required Tools](/installation/cloud/aws#required-tools)
  + [AWS CLI Installation](/installation/cloud/aws#aws-cli-installation)
  + [eksctl Installation](/installation/cloud/aws#eksctl-installation)
* [AWS Authentication](/installation/cloud/aws#aws-authentication)
* [Required IAM Permissions](/installation/cloud/aws#required-iam-permissions)
* [Assumptions](/installation/cloud/aws#assumptions)
* [Required Accesses and Permissions](/installation/cloud/aws#required-accesses-and-permissions)
* [EKS Cluster Creation](/installation/cloud/aws#eks-cluster-creation)
* [EKS Core Addons](/installation/cloud/aws#eks-core-addons)
* [AWS-Specific Storage Classes](/installation/cloud/aws#aws-specific-storage-classes)
  + [EBS gp3 (RWO) - Database Storage](/installation/cloud/aws#ebs-gp3-rwo---database-storage)
  + [EFS (RWX) - Shared Storage](/installation/cloud/aws#efs-rwx---shared-storage)
* [EFS File System Creation](/installation/cloud/aws#efs-file-system-creation)
* [DNS Configuration (Route53)](/installation/cloud/aws#dns-configuration-route53)
* [Container Registry](/installation/cloud/aws#container-registry)
* [Authentication and Identity](/installation/cloud/aws#authentication-and-identity)
* [Integrations (Endpoints and Tokens)](/installation/cloud/aws#integrations-endpoints-and-tokens)
* [VS Code Extensions](/installation/cloud/aws#vs-code-extensions)
* [Network and Security](/installation/cloud/aws#network-and-security)
* [What to Prepare Before Running the Installer](/installation/cloud/aws#what-to-prepare-before-running-the-installer)
* [AWS EKS Checklist](/installation/cloud/aws#aws-eks-checklist)
* [See Also](/installation/cloud/aws#see-also)

---


# ORIGEM: https://docs.wynxx.app/feature-benchmark/code-tester
Feature Benchmark

# Code Tester

This page explains how to use the Code Tester Feature Benchmark to evaluate and compare different language models (LLMs) for test generation. Follow the steps below to benchmark LLM performance in creating unit, integration, and functional tests.

## Overview

The Code Tester Benchmark allows you to evaluate and compare, side-by-side, the ability of different language models to generate or execute code tests using controlled parameters. This makes it easier to identify which model produces more complete, correct, and context-appropriate tests.

### Purpose and Benefits

**Purpose**: Direct comparison between two models (LLM #1 and LLM #2) with the same code and parameters for objective evaluation of test generation quality.

**Benefits**:

* Direct side-by-side comparison of two LLMs
* Objective evaluation of generated test quality
* Use of source code and existing tests as reference
* Standardized test framework configuration
* Time savings in validating models for test automation
* Practical application for software quality and CI/CD scenarios

![Code Tester - Benchmark Interface](/assets/code-tester-benchmark-homepage-CrMHMVmu.png)

## Access

* Go to Feature Benchmark → Code Tester
* Access the benchmark interface to compare LLM performance

## Interface Overview

The Code Tester Benchmark interface provides:

* **Parameters section**: Configure Source Language, Prompt, and optional settings
* **Reference files**: Upload supporting files for test generation
* **Existing Test**: Include existing tests for comparison
* **Test Framework**: Select appropriate testing framework
* **Show source code**: Expandable section to view/edit source code (displays line count and character count)
* **LLM comparison panels**: Side-by-side view with LLM #1 and LLM #2
* **Status indicators**: Shows job status for each LLM panel
* **Tutorial button**: Access to guided instructions

![Code Tester - Benchmark Interface](/assets/code-tester-benchmark-homepage-CrMHMVmu.png)

## Configuration Parameters

### Required Parameters

**Source Language**

* Select the programming language for your code
* Supports major languages (Python, Java, C#, JavaScript, etc.)

**Prompt**

* Select from available prompts specific to your chosen test type
* Each test type has dedicated prompts optimized for that testing scenario

### Optional Parameters

**Reference Files** (📁 icon)

* Upload supporting files to assist in test creation
* Include documentation, specifications, or related code files
* Helps LLMs understand context and requirements

**Existing Test** (🧪 icon)

* Upload existing tests for comparison or improvement
* Use as baseline for quality evaluation
* Helps maintain consistency with current test patterns

**Test Framework** (⚙️ icon)

* Select appropriate testing framework for your language
* Examples: JUnit (Java), PyTest (Python), NUnit (C#), Jest (JavaScript)
* Ensures generated tests follow framework conventions

## Test Types and Configuration

### Unit Test

Generate and compare unit tests for individual functions and methods.

**Fields**:

* **Source Language**: Programming language (Python, Java, C#, JavaScript, etc.)
* **Prompt**: Select from available unit test prompts:
  + `CreateUnitTests_V1`: Generate unit tests for individual functions and methods
  + `CreateUnitTests_Chain_V1`: Generate chained unit tests with dependencies
* **Reference Files** (optional): Reference files to assist in test creation
* **Existing Test** (optional): Already existing tests for comparison or improvement
* **Test Framework** (optional): Unit testing framework (JUnit, PyTest, NUnit, etc.)

![Code Tester - Unit Test Configuration](/assets/unit-test-example-BKq1BW-f.png)

### Integration Test

Create and compare tests that validate interaction between multiple modules or services.

**Fields**:

* **Source Language**: Programming language
* **Prompt**: Select from available integration test prompts:
  + `CreateIntegrationTests_V1`: Create tests that validate interaction between modules or services
* **Reference Files** (optional): Files representing integrated components or services
* **Existing Test** (optional): Existing integration tests for reference
* **Test Framework** (optional): Integration testing framework (Spring Test, Mocha, Cypress, etc.)

![Code Tester - Integration Test Configuration](/assets/integration-test-example-DarhOZxe.png)

### Functional Test

Generate tests that validate system behavior according to functional requirements.

**Fields**:

* **Source Language**: Programming language
* **Prompt**: Select from available functional test prompts to validate system behavior according to functional requirements
* **Reference Files** (optional): Documents, scripts, or functional specifications
* **Existing Test** (optional): Existing functional tests for reference
* **Test Framework** (optional): Functional testing tools (Selenium, Robot Framework, Cypress, etc.)

![Code Tester - Functional Test Configuration](/assets/functional-test-example-QSTMJXBc.png)

## Step-by-Step Process

### Setup and Configuration

1. **Access Code Tester Benchmark**

   * Navigate to Feature Benchmark → Code Tester
   * The interface will display the parameters section and two LLM comparison panels
2. **Configure Parameters**

   * **Source Language**: Select from the dropdown menu
   * **Prompt**: Select from test-type specific prompts (Unit: CreateUnitTests\_V1, CreateUnitTests\_Chain\_V1; Integration: CreateIntegrationTests\_V1)
   * **Reference Files** (optional): Click the 📁 icon to upload supporting files
   * **Existing Test** (optional): Click the 🧪 icon to upload existing tests
   * **Test Framework** (optional): Click the ⚙️ icon to select framework
3. **Add Source Code**

   * Click "Show source code" to expand the code editor section
   * The section shows "1 lines • 31 chars" indicating current content
   * Paste your code directly or drag and drop files
   * The editor supports syntax highlighting for the selected language
4. **Select LLM Models**

   * Choose different models for LLM #1 and LLM #2 panels
   * Each panel shows "Status: - | Job: -" initially
   * Models will be compared side-by-side for objective evaluation
5. **Run the Benchmark**

   * Click the run/start button to begin test generation
   * Monitor status updates in each LLM panel
   * Wait for both models to complete processing
6. **Analyze Results**

   * Compare generated tests in both panels
   * Evaluate based on quality, coverage, and appropriateness
   * Use the Tutorial button for guidance on evaluation criteria

### Unit Test Specific Steps

**Additional Considerations**:

1. In the **Prompt**:

   * Select the appropriate prompt that covers functions and methods to be tested
   * Choose prompts that include edge cases
   * Select prompts that handle expected exceptions
2. Ensure chosen **Test Framework** is compatible with:

   * Selected programming language
   * Unit testing best practices
3. **Evaluation Criteria**:

   * Code coverage completeness
   * Clarity of generated test cases
   * Compliance with testing best practices
   * Proper assertion usage

### Integration Test Specific Steps

**Additional Considerations**:

1. In the **Prompt**:

   * Select prompts that cover modules or services that should interact
   * Choose prompts for integration conditions and scenarios
   * Select prompts that cover expected communication patterns
2. Ensure **Test Framework** is suitable for:

   * Multi-component testing
   * Service integration scenarios
3. **Evaluation Criteria**:

   * Communication between components
   * Fault detection capabilities
   * Test execution efficiency
   * Realistic integration scenarios

### Functional Test Specific Steps

**Additional Considerations**:

1. In the **Prompt**:

   * Select prompts that cover functionalities to be verified
   * Choose prompts for functional requirements validation
   * Select prompts that test complete usage flows
2. Choose appropriate **Test Framework** for:

   * End-to-end functional testing
   * User workflow validation
3. **Evaluation Criteria**:

   * Compliance with functional requirements
   * Clarity and maintainability of test scripts
   * Coverage of real usage scenarios
   * User experience validation

![Code Tester - Results Comparison](/assets/compare-results-kl3pcSLs.png)

## Tips

* **Use appropriate prompts**: Select prompts that clearly match your testing requirements for better test generation quality
* **Monitor the status indicators**: Watch the "Status" and "Job" fields in each LLM panel for progress updates
* **Leverage the source code editor**: The expandable "Show source code" section shows line and character counts to help gauge complexity
* **Use the Tutorial button**: Access built-in guidance for optimal benchmark usage
* **Upload comprehensive reference files**: Supporting documentation helps LLMs understand context better
* **Include existing tests as baselines**: Use current tests to evaluate improvement and consistency
* **Choose appropriate test frameworks**: Ensure selected frameworks align with your development stack
* **Compare systematically**: Use consistent evaluation criteria across both LLM panels

## Troubleshooting

* **Status not updating**: Refresh the page if LLM panels show stuck status indicators
* **Poor test quality**: Try different available prompts that better match your requirements and add reference files
* **Framework compatibility issues**: Verify the selected test framework supports your programming language
* **Source code editor issues**: If the "Show source code" section doesn't expand, try refreshing or re-uploading files
* **Job failures**: Check that uploaded files are in supported formats and source code is syntactically correct
* **Model comparison errors**: Ensure both selected LLMs support the chosen programming language and test type
* **Performance issues**: For large codebases, consider breaking down into smaller, focused test scenarios

## Best Practices

* Start with simple test scenarios before moving to complex ones
* Use existing tests as quality benchmarks
* Document evaluation criteria before running benchmarks
* Consider multiple test types for comprehensive model evaluation
* Save successful configurations for future use

Learn more about testing approaches:

* [Code Tester Feature Guide](/guides/features/code-tester)
* [Functional Tester Feature](/guides/features/functional-tester)

Last modified on February 26, 2026

[Code Documenter](/feature-benchmark/code-documenter)[Overview](/legacy-uplift/legacy-uplift)

On this page

* [Overview](/feature-benchmark/code-tester#overview)
  + [Purpose and Benefits](/feature-benchmark/code-tester#purpose-and-benefits)
* [Access](/feature-benchmark/code-tester#access)
* [Interface Overview](/feature-benchmark/code-tester#interface-overview)
* [Configuration Parameters](/feature-benchmark/code-tester#configuration-parameters)
  + [Required Parameters](/feature-benchmark/code-tester#required-parameters)
  + [Optional Parameters](/feature-benchmark/code-tester#optional-parameters)
* [Test Types and Configuration](/feature-benchmark/code-tester#test-types-and-configuration)
  + [Unit Test](/feature-benchmark/code-tester#unit-test)
  + [Integration Test](/feature-benchmark/code-tester#integration-test)
  + [Functional Test](/feature-benchmark/code-tester#functional-test)
* [Step-by-Step Process](/feature-benchmark/code-tester#step-by-step-process)
  + [Setup and Configuration](/feature-benchmark/code-tester#setup-and-configuration)
  + [Unit Test Specific Steps](/feature-benchmark/code-tester#unit-test-specific-steps)
  + [Integration Test Specific Steps](/feature-benchmark/code-tester#integration-test-specific-steps)
  + [Functional Test Specific Steps](/feature-benchmark/code-tester#functional-test-specific-steps)
* [Tips](/feature-benchmark/code-tester#tips)
* [Troubleshooting](/feature-benchmark/code-tester#troubleshooting)
* [Best Practices](/feature-benchmark/code-tester#best-practices)

---


# ORIGEM: https://docs.wynxx.app/integrations/sast/csv
Getting Started

# What is Wynxx?

Wynxx is a comprehensive AI development platform that accelerates software development through intelligent automation. The platform provides a suite of AI-powered tools accessible via web portal and VS Code extension.

---

## Platform Features

### Code Generation & Testing

| Feature | Description | Access |
| --- | --- | --- |
| 🧪 **[Code Tester](/guides/features/code-tester)** | Generate unit and integration tests automatically from source code | Portal, VS Code |
| 🎭 **[Functional Tester](/guides/features/functional-tester)** | Drive Playwright end-to-end tests using natural language prompts | Playwright Integration |

### Code Quality & Documentation

| Feature | Description | Access |
| --- | --- | --- |
| 📝 **[Code Documenter](/guides/features/code-documenter)** | Transform source code into clear, structured documentation | Portal, VS Code |
| 🔧 **[Code Fixer](/guides/features/code-fixer)** | Automatically fix code issues detected by SAST tools (SonarQube, etc.) | Portal |
| 🗞️ **[Code Reviewer](/api-guides/code-reviewer)** | Automated code review and feedback for pull requests | API |

### AI Assistants

| Feature | Description | Access |
| --- | --- | --- |
| 💬 **[Code Dialoguer](/guides/features/code-dialoguer)** | Interactive chat for code questions, explanations, and refactoring | Portal, VS Code |
| 🏗️ **[Architect AI](/wynxx-assist-extension/Wynxx-Assist-Architect)** | Generate architecture diagrams (UML, Sequence, Class) from code | VS Code |

### Product & Requirements

| Feature | Description | Access |
| --- | --- | --- |
| 🧠 **[Story Creator 2.0](/guides/features/story-creator2)** | Transform use cases into structured backlogs with epics, features, and user stories | Portal |
| 📋 **[Story Creator 1.0](/guides/features/story-creator)** | Legacy story creation interface | Portal |

### Legacy Modernization

| Feature | Description | Access |
| --- | --- | --- |
| 🔄 **[Legacy Transformer](/wynxx-assist-extension/Wynxx-Assist-Legacy)** | Extract business rules from legacy code (COBOL, JCL) and convert to modern languages or user stories | VS Code |

---

## Access Methods

### Web Portal

The main Wynxx web application provides access to:

* Code Documenter, Code Tester, Code Fixer
* Code Dialoguer, Story Creator
* Settings, User Management, Cost Control

### VS Code Extension (Wynxx Assist)

The [Wynxx Assist Extension](/wynxx-assist-extension/wynxx-assist) brings AI capabilities directly into your IDE:

* **Architect AI** - Generate architecture diagrams
* **Code Documenter** - Document code in-editor
* **Code Tester** - Generate tests without leaving VS Code
* **Code Dialoguer** - AI chat integrated in your workflow
* **Legacy Transformer** - Modernize legacy code

### API

All features are accessible via REST API for CI/CD integration and automation. See the [API Reference](/api) for details.

---

## Administration Features

| Feature | Description |
| --- | --- |
| 👥 **[User Control](/settings/user-control)** | Manage users, roles, and permissions |
| 👪 **[Group Control](/settings/group-control)** | Organize users into groups with shared settings |
| 💰 **[Cost Control](/settings/cost-control)** | Monitor LLM usage, set budgets, and track spending |
| ⚙️ **[Application Settings](/settings/application)** | Configure LLMs, integrations, and system settings |
| 🧙 **[Setup Wizard](/settings/wizard)** | Guided initial configuration |

---

## Integrations

### LLM Providers

| Provider | Chat Models | Embedding Models |
| --- | --- | --- |
| **Azure OpenAI** | GPT-5, GPT-4.1, GPT-4o | text-embedding-3-large |
| **AWS Bedrock** | Claude Opus 4.5, Claude Sonnet 4.5, Claude Sonnet 3.7 | amazon.titan-embed-text-v1 |
| **Google Gemini** | Gemini 2.5 Pro | text-embedding-004 |
| **LiteLLM** | Multi-provider gateway | - |

### Version Control Systems

| Platform | Supported Version |
| --- | --- |
| **GitHub** | GitHub Cloud |
| **GitLab** | API v4 (Cloud & Self-hosted) |
| **Azure DevOps** | Cloud |

### SAST Tools

| Tool | Supported Version |
| --- | --- |
| **SonarQube** | 2025.x |
| **SonarCloud** | 2025.x |

### Agile Tools

| Tool | Supported Version |
| --- | --- |
| **Jira** | Jira Cloud & Jira Server (API v3) |
| **Azure Boards** | Cloud |

---

## Quick Links

* [Prerequisites](/installation/prerequisites) - Requirements checklist
* [Installation](/installation) - Deploy Wynxx on your infrastructure
* [FAQ](/getting-started/faq) - Common questions and troubleshooting
* [API Reference](/api) - REST API documentation

Last modified on February 26, 2026

[FAQ (Frequently Asked Questions)](/getting-started/faq)

On this page

* [Platform Features](/introduction#platform-features)
  + [Code Generation & Testing](/introduction#code-generation--testing)
  + [Code Quality & Documentation](/introduction#code-quality--documentation)
  + [AI Assistants](/introduction#ai-assistants)
  + [Product & Requirements](/introduction#product--requirements)
  + [Legacy Modernization](/introduction#legacy-modernization)
* [Access Methods](/introduction#access-methods)
  + [Web Portal](/introduction#web-portal)
  + [VS Code Extension (Wynxx Assist)](/introduction#vs-code-extension-wynxx-assist)
  + [API](/introduction#api)
* [Administration Features](/introduction#administration-features)
* [Integrations](/introduction#integrations)
  + [LLM Providers](/introduction#llm-providers)
  + [Version Control Systems](/introduction#version-control-systems)
  + [SAST Tools](/introduction#sast-tools)
  + [Agile Tools](/introduction#agile-tools)
* [Quick Links](/introduction#quick-links)

---


# ORIGEM: https://docs.wynxx.app/releases/v3.0.0
Release Notes

# Wynxx v3.0.0 Release Notes

> ⚠️ **Important:** Please read the full article before updating your environment.

Welcome to **Wynxx 3.0**! We are pleased to announce this major milestone in the evolution of the product. This version combines a new visual identity, significant improvements in user experience, and a strategic architectural evolution, positioning Wynxx for continuous, intelligent, and scalable growth.

## 🚀 Release Highlights

### ✨ Key Features and Updates

* **🎨 New Visual Identity**: Complete redesign with Light/Dark mode support
* **🏠 Modern Home Screen**: Redesigned interface with shortcut cards and usage highlights
* **🧩 Micro Frontends Architecture**: Enhanced flexibility and scalability
* **🧠 Agentic Architecture**: AI Agent Catalogs and Intelligent Orchestrator
* **📝 Story Creator 2.0**: Next generation backlog creation with personas
* **🤖 Smart P.O.**: Intelligent Product Owner chatbot

---

## 🎨 New Visual Identity & User Experience

Wynxx 3.0 introduces a complete redesign of the visual identity, focused on delivering a more modern, intuitive, and user-friendly experience.

### Key highlights:

* **Light Mode and Dark Mode**: Allowing users to choose their preferred visual experience
* **Multi-language support**: Increasing accessibility and global adoption
* **Cleaner, more consistent interface**: Designed to improve productivity

This new identity reinforces Wynxx's positioning as a modern, user-centric platform built to scale.

---

## 🏠 New Home Screen

The home screen has been fully redesigned to provide more context and faster access to key capabilities right from the start.

### Highlights include:

* **Shortcut cards** for Wynxx's main features
* **Usage highlights**: Showcasing consumption indicators for the most relevant features
* **Strategic overview** of platform usage, supporting better visibility and decision-making

---

## 🧩 Modern Architecture with Micro Frontends

From a technical perspective, the new Wynxx 3.0 interface was built using a **Micro Frontends architecture**.

### This approach brings several benefits:

* **Increased flexibility and scalability**
* **Easier future feature integration**
* **Independent evolution** by different teams
* **Reduced impact and risk** during changes and maintenance

This architectural foundation ensures Wynxx can grow in a modular and sustainable way.

---

## 🧠 Architectural Evolution: Agentic Architecture

Beyond visual and structural modernization, Wynxx 3.0 takes a strategic step forward with the introduction of an **Agentic Architecture**, aligned with modern AI-driven system design principles.

### This new approach includes:

* **AI Agent Catalogs**: Enabling the organization, versioning, and reuse of specialized agents
* **Intelligent Agent Orchestrator**: Responsible for dynamically coordinating multiple agents based on context and objectives

With this architecture, Wynxx features can leverage AI Agents to execute activities in a more autonomous, intelligent, and efficient manner, fully unlocking the potential of Large Language Models (LLMs).

### Key benefits:

* **Increased operational intelligence** across existing and future features
* **Ability to combine multiple agents** to handle complex workflows
* **Faster and safer development** of new agent-based features
* **Future-proof foundation** that supports continuous evolution and expansion of agents and capabilities

This evolution positions Wynxx as a platform ready for the next generation of AI-powered SDLC solutions.

---

## 📝 Story Creator 2.0 — The Next Generation of Backlog Creation

With Wynxx 3.0, we are launching **Story Creator 2.0**, a major evolution in how teams create, organize, and refine backlog items.

### Key improvements:

* **More granular experience**: With support for projects to group and manage backlogs
* **Introduction of the Persona concept**:
  + Enables different backlog creation profiles
  + Example: financial, technical, product, or business-oriented personas
  + Each persona guides language, structure, and focus of generated items
* **Improved backlog management**: With clearer visualization of item hierarchy
* **Visibility of parent items**: Making dependencies, context, and traceability easier to understand

Story Creator 2.0 was designed to reduce friction in refinement and increase the quality of backlog items.

---

## 🤖 Smart P.O. — Intelligent Product Owner

Another key addition in Wynxx 3.0 is **Smart P.O. (Product Owner)**, an intelligent chatbot that supports users during User Story refinement.

### With Smart P.O., users can:

* **Refine stories** in a more structured way
* **Receive intelligent suggestions** for improvement
* **Ensure better clarity, consistency, and alignment** with backlog best practices

Smart P.O. acts as a product copilot, supporting teams in their daily product management activities.

---

## 🔄 Updates to Existing Features

All other Wynxx features have been **visually refreshed** to align with the new layout and visual identity.

**Important**: While the look and feel have been updated, existing usability patterns have been preserved, ensuring a smooth transition for current users.

---

## 🚦 Availability & Access Model

Wynxx 3.0 is initially being released under **Controlled Availability (CA)**, followed by **Global Availability (GA)** at a later stage.

### This phased rollout allows us to:

* **Closely monitor usage and performance**
* **Collect early feedback** from selected users
* **Ensure stability and quality** before broad adoption

To learn more about the Controlled Availability (CA) and Global Availability (GA) process, please refer to the link below:

🔗 **Wynxx CA & GA Policy**

### Request Access to Controlled Availability (CA)

If you would like to participate in the Controlled Availability phase, please register using the form below to be added to the waiting list:

🔗 **Wynxx 3.0 - Interest in Controlled Availability (CA) Release – Fill out form**

Access will be granted progressively based on availability and eligibility.

---

## 📄 Documentation & Support

* Full product documentation: **Wynxx Portal**
* For technical support and questions, contact your designated support channel

**Note**: Container image versions and Helm chart details will be provided when the release moves from Controlled Availability to Global Availability.

---

✅ This concludes the Release Notes for version **3.0.0**.
Welcome to the next generation of Wynxx!

Last modified on February 26, 2026

[Code Tester](/wynxx-assist-extension/Wynxx-Assist-Test)

On this page

* [🚀 Release Highlights](/releases/v3.0.0#-release-highlights)
  + [✨ Key Features and Updates](/releases/v3.0.0#-key-features-and-updates)
* [🎨 New Visual Identity & User Experience](/releases/v3.0.0#-new-visual-identity--user-experience)
  + [Key highlights:](/releases/v3.0.0#key-highlights)
* [🏠 New Home Screen](/releases/v3.0.0#-new-home-screen)
  + [Highlights include:](/releases/v3.0.0#highlights-include)
* [🧩 Modern Architecture with Micro Frontends](/releases/v3.0.0#-modern-architecture-with-micro-frontends)
  + [This approach brings several benefits:](/releases/v3.0.0#this-approach-brings-several-benefits)
* [🧠 Architectural Evolution: Agentic Architecture](/releases/v3.0.0#-architectural-evolution-agentic-architecture)
  + [This new approach includes:](/releases/v3.0.0#this-new-approach-includes)
  + [Key benefits:](/releases/v3.0.0#key-benefits)
* [📝 Story Creator 2.0 — The Next Generation of Backlog Creation](/releases/v3.0.0#-story-creator-20--the-next-generation-of-backlog-creation)
  + [Key improvements:](/releases/v3.0.0#key-improvements)
* [🤖 Smart P.O. — Intelligent Product Owner](/releases/v3.0.0#-smart-po--intelligent-product-owner)
  + [With Smart P.O., users can:](/releases/v3.0.0#with-smart-po-users-can)
* [🔄 Updates to Existing Features](/releases/v3.0.0#-updates-to-existing-features)
* [🚦 Availability & Access Model](/releases/v3.0.0#-availability--access-model)
  + [This phased rollout allows us to:](/releases/v3.0.0#this-phased-rollout-allows-us-to)
  + [Request Access to Controlled Availability (CA)](/releases/v3.0.0#request-access-to-controlled-availability-ca)
* [📄 Documentation & Support](/releases/v3.0.0#-documentation--support)

---


# ORIGEM: https://docs.wynxx.app/legacy-transformer-web/overview
Legacy Transformer Web

# Overview

Transform your legacy applications into modern, maintainable, and scalable systems through automated analysis, domain classification, business rule extraction, and code generation.

![Legacy Transformer Web Overview](/assets/legacy-transformer-overview-CVBYrzwm.png)

## Modernization Workflow

Legacy Transformer Web follows a structured three-phase approach to modernize your applications:

### Phase A: Scan & Analysis

Automated analysis of legacy repository code

* Repository code scanning
* File structure analysis
* Dependency mapping
* Domain identification

### Phase B: Review Results

Domain classification and business rules extraction

* Domain validation
* Business rule documentation
* Code inventory management
* Rule refinement

### Phase C: Submit for Modernization

Generate modern backend and frontend code

* Archetype-based code generation
* Modern framework implementation
* Clean architecture patterns
* Scalable system design

## Key Features

**Automated Analysis**

* Complete codebase scanning
* Language detection
* Dependency analysis
* Code metrics calculation

**Domain Classification**

* Business logic identification
* Infrastructure separation
* File categorization
* Domain-driven design

**Business Rules Extraction**

* Automated rule discovery
* Documentation generation
* Rule validation
* Manual refinement

**Code Generation**

* Modern architecture patterns
* Backend and frontend generation
* Archetype-based templates
* Scalable implementations

## Getting Started

### Step 1: Create an Archetype

Before creating projects, define code generation templates.

### Step 2: Create a Project

Set up your modernization project.

### Step 3: Start Analysis

Begin the automated code analysis.

[Complete Getting Started Guide →](/legacy-transformer-web/getting-started)

## Use Cases

**Legacy System Modernization**

* Mainframe to cloud migration
* Monolith to microservices
* Technology stack upgrades
* Architecture modernization

**Code Quality Improvement**

* Technical debt reduction
* Documentation generation
* Dependency optimization
* Performance enhancement

**Business Logic Preservation**

* Rule extraction and documentation
* Domain knowledge capture
* Logic modernization
* Compliance maintenance

## Benefits

**Accelerated Migration**

* Automated analysis reduces manual effort
* Pattern-based code generation
* Consistent modernization approach
* Reduced time-to-market

**Risk Mitigation**

* Comprehensive analysis before changes
* Business rule preservation
* Incremental modernization
* Quality validation

**Cost Efficiency**

* Automated processes reduce costs
* Reusable archetypes
* Minimal manual intervention
* Predictable outcomes

---

Ready to modernize your legacy application? Start with the [Getting Started Guide →](/legacy-transformer-web/getting-started) to begin your transformation journey.

Last modified on February 26, 2026

[Transcriptions](/legacy-uplift/transcriptions)[Getting Started](/legacy-transformer-web/getting-started)

On this page

* [Modernization Workflow](/legacy-transformer-web/overview#modernization-workflow)
  + [Phase A: Scan & Analysis](/legacy-transformer-web/overview#phase-a-scan--analysis)
  + [Phase B: Review Results](/legacy-transformer-web/overview#phase-b-review-results)
  + [Phase C: Submit for Modernization](/legacy-transformer-web/overview#phase-c-submit-for-modernization)
* [Key Features](/legacy-transformer-web/overview#key-features)
* [Getting Started](/legacy-transformer-web/overview#getting-started)
  + [Step 1: Create an Archetype](/legacy-transformer-web/overview#step-1-create-an-archetype)
  + [Step 2: Create a Project](/legacy-transformer-web/overview#step-2-create-a-project)
  + [Step 3: Start Analysis](/legacy-transformer-web/overview#step-3-start-analysis)
* [Use Cases](/legacy-transformer-web/overview#use-cases)
* [Benefits](/legacy-transformer-web/overview#benefits)

---


# ORIGEM: https://docs.wynxx.app/legacy-uplift/legacy-uplift
Legacy Uplift

# Overview

Automate code migrations with AI. Move from one framework to another safely and efficiently.

## Recipes

Create step-by-step migration instructions.

* Define transformation rules
* Set file patterns and commands
* Reuse across multiple projects

## Transcriptions

Execute your recipes on actual repositories.

* Connect your Git repository
* Select recipe and AI model
* Get a new branch with migrated code

## Analytics

Track migration success with real-time metrics.

* Success rates and failure analysis
* Processing times and code changes
* Recipe performance comparison

## Getting Started

1. **Create** migration rules in [Recipes →](/legacy-uplift/recipes)
2. **Execute** migrations in [Transcriptions →](/legacy-uplift/transcriptions)
3. **Monitor** results in [Analytics →](/legacy-uplift/analytics)

## Use Cases

* Language upgrades (Java 8 → 11, Python 2 → 3)
* Framework migrations (Angular, Spring Boot, React)
* Dependency updates and API changes

Last modified on February 26, 2026

[Code Tester](/feature-benchmark/code-tester)[Analytics](/legacy-uplift/analytics)

On this page

* [Recipes](/legacy-uplift/legacy-uplift#recipes)
* [Transcriptions](/legacy-uplift/legacy-uplift#transcriptions)
* [Analytics](/legacy-uplift/legacy-uplift#analytics)
* [Getting Started](/legacy-uplift/legacy-uplift#getting-started)
* [Use Cases](/legacy-uplift/legacy-uplift#use-cases)

---


# ORIGEM: https://docs.wynxx.app/legacy-uplift/analytics
Legacy Uplift

# Analytics

Track your migration progress with real-time data and comprehensive metrics. The Analytics dashboard shows success rates, processing stats, and helps you optimize your transformations.

## Analytics Dashboard Overview

![Analytics Dashboard](/assets/analytics-dashboard-B6d0wvJ3.png)

The dashboard shows "Comprehensive insights into your code transformation operations" with time filters (Daily/Last 30 days) and key metrics at a glance.

### Key Metrics Cards

* **Total Transformations**: Shows total count with pending and in-progress status
* **Success Rate**: Percentage with successful vs failed breakdown
* **Files Processed**: Total files with average per transformation

## Status Breakdown

Visual bar chart showing the distribution of your transformations:

![Status Breakdown Chart](/assets/status-breakdown-BUtHon_F.png)

* **Completed**: Successfully finished transformations (e.g., 95.2%)
* **Failed**: Transformations with errors (e.g., 4.8%)

The chart displays the exact count and percentage for each status, helping you quickly assess migration health.

## Top Recipes by Usage

Table showing which recipes are being used most and their performance:

![Top Recipes Chart](/assets/top-recipes-chart-CwP8M7OR.png)

**Table columns:**

* **Recipe Name**: Type of migration (e.g., "Java 11 to 21 with spring", "Angular 9 to Angular 10")
* **Usage**: How many times the recipe has been executed
* **Success Rate**: Percentage of successful completions (100.0% or 0.0%)
* **Files**: Total files processed with this recipe
* **Tokens**: AI tokens consumed (e.g., 336.2K)
* **Avg Time**: Average processing duration (e.g., 1m 29s)

**Example data shown:**

* Java migrations: 20 uses, 100% success, 240 files, 1m 29s average
* Angular migrations: 1 use, 0% success, 1 file, 0.7s average

## Code Transformation Metrics

Detailed breakdown of your code changes:

![Code Transformation Metrics](/assets/code-metrics-BCBb_G5C.png)

**Key metrics displayed:**

* **Original Lines of Code**: Starting codebase size (e.g., 10,940 lines)
* **Transformed Lines of Code**: Final codebase size (e.g., 10,661 lines)
* **Net Change**: Difference between original and transformed (e.g., -279 lines)
* **Avg Files per Transformation**: Typical file count per migration (e.g., 11.5 files)
* **Avg Processing Time**: Average duration per transformation (e.g., 84.4s)
* **Total Files Processed**: Sum of all files across migrations (e.g., 241 files)

**Understanding Net Change:**

* **Negative values** (like -279): Code became more efficient, removed redundant lines
* **Positive values**: Additional code was needed, might need review

## Dashboard Filters

**Time Period Options:**

* **Daily dropdown**: Hourly, Daily, Weekly, Monthly
* **Date range dropdown**: Last 7 days, Last 30 days, Last 90 days, Last year

Filters help you analyze trends over different time periods and compare performance across various timeframes.

## Using Analytics for Optimization

### Identify Best Practices

* Study successful recipes and replicate their patterns
* Understand optimal project sizes and structures
* Learn from high-performing teams and their approaches
* Recognize effective LLM configurations

### Improve Success Rates

* Analyze failure patterns to refine recipes
* Identify common pitfalls and create preventive measures
* Optimize processing parameters based on performance data
* Develop troubleshooting guides from analytics insights

### Resource Management

* Plan capacity based on historical usage patterns
* Optimize cost by selecting efficient LLMs for specific tasks
* Schedule migrations during off-peak periods
* Balance workload across available resources

### Continuous Improvement

* Monitor metrics regularly to spot declining performance
* Update recipes based on analytics feedback
* Train teams using data-driven insights
* Evolve processes based on measurable outcomes

## Dashboard Navigation

### Filtering and Segmentation

* **Time Periods**: Daily, weekly, monthly, and custom ranges
* **Recipe Types**: Filter by specific migration patterns
* **Project Categories**: Segment by application type or team
* **Status Filters**: Focus on successful, failed, or pending transformations

### Export and Reporting

* **Data Export**: Download analytics data for external analysis
* **Custom Reports**: Generate reports for stakeholders
* **Scheduled Reports**: Automated reporting for management
* **API Access**: Integrate analytics into other tools

### Real-time Monitoring

* **Live Updates**: See metrics update as transformations complete
* **Alerts**: Notifications for critical issues or milestones
* **Dashboard Refresh**: Automatic data synchronization
* **Status Indicators**: Real-time health monitoring

## Best Practices for Analytics Usage

### Regular Monitoring

* Check dashboard weekly for trend identification
* Review failed transformations promptly
* Monitor resource usage patterns
* Track team adoption and success rates

### Data-Driven Decisions

* Use analytics to guide recipe improvements
* Base capacity planning on historical data
* Make technology choices using performance metrics
* Justify Legacy Uplift investments with concrete ROI data

### Team Collaboration

* Share analytics insights across teams
* Use metrics in retrospectives and planning sessions
* Create accountability through transparent measurement
* Celebrate successes highlighted by analytics

---

Congratulations! You now understand all three pillars of Legacy Uplift. Ready to start your migration journey? Begin with [Recipes →](/legacy-uplift/recipes)

Last modified on February 26, 2026

[Overview](/legacy-uplift/legacy-uplift)[Recipes](/legacy-uplift/recipes)

On this page

* [Analytics Dashboard Overview](/legacy-uplift/analytics#analytics-dashboard-overview)
  + [Key Metrics Cards](/legacy-uplift/analytics#key-metrics-cards)
* [Status Breakdown](/legacy-uplift/analytics#status-breakdown)
* [Top Recipes by Usage](/legacy-uplift/analytics#top-recipes-by-usage)
* [Code Transformation Metrics](/legacy-uplift/analytics#code-transformation-metrics)
* [Dashboard Filters](/legacy-uplift/analytics#dashboard-filters)
* [Using Analytics for Optimization](/legacy-uplift/analytics#using-analytics-for-optimization)
  + [Identify Best Practices](/legacy-uplift/analytics#identify-best-practices)
  + [Improve Success Rates](/legacy-uplift/analytics#improve-success-rates)
  + [Resource Management](/legacy-uplift/analytics#resource-management)
  + [Continuous Improvement](/legacy-uplift/analytics#continuous-improvement)
* [Dashboard Navigation](/legacy-uplift/analytics#dashboard-navigation)
  + [Filtering and Segmentation](/legacy-uplift/analytics#filtering-and-segmentation)
  + [Export and Reporting](/legacy-uplift/analytics#export-and-reporting)
  + [Real-time Monitoring](/legacy-uplift/analytics#real-time-monitoring)
* [Best Practices for Analytics Usage](/legacy-uplift/analytics#best-practices-for-analytics-usage)
  + [Regular Monitoring](/legacy-uplift/analytics#regular-monitoring)
  + [Data-Driven Decisions](/legacy-uplift/analytics#data-driven-decisions)
  + [Team Collaboration](/legacy-uplift/analytics#team-collaboration)

---


# ORIGEM: https://docs.wynxx.app/legacy-uplift/recipes
Legacy Uplift

# Recipes

Before migrating, define the rules of the game. In the Recipes section, you can create custom migration recipes that serve as step-by-step guides for transforming your code from one framework to another.

## What are Recipes?

Recipes are configurable migration blueprints that define:

* **What** needs to be transformed
* **How** the transformation should occur
* **Which** specific rules the engine must follow
* **In what order** the steps should be executed

Think of recipes as your migration playbook - a reusable set of instructions that can be applied across multiple repositories and projects.

## Creating Your First Recipe

### Step 1: Start Creating

1. Go to the **Recipes** option in your Wynxx dashboard
2. Click the **"New Recipe"** button in the top right corner
3. Select **"New Recipe"** from the dropdown menu (you can also Import or Export recipes)
4. You'll be presented with the "Create New Recipe" form

![Create New Recipe Form](/assets/create-new-recipe-form-mZj4gWZo.png)

### Step 2: Basic Information

Fill in the essential recipe details in the **Basic Information** section:

* **Recipe Name**: Give your recipe a descriptive name (e.g., ".NET Core 3.1 to .NET 8 Migration")
* **Version**: Set the recipe version (defaults to "1.0")
* **Description**: Provide a clear explanation of what this recipe accomplishes

![Recipe Basic Information](/assets/recipe-basic-info-D0mKekND.png)

### Step 3: Add Recipe Steps

In the **Recipe Steps** section:

1. Click **"+ Add Step"** or **"+ Add your first step"** to begin
2. Define the step name (e.g., "Update C# Files")
3. Add a step description explaining what this step accomplishes
4. Configure the migration instructions for this step

![Recipe Steps Configuration](/assets/recipe-steps-config-Du-ykukt.png)

### Step 4: Configure Instructions

For each step, you need to configure:

#### Instruction Details

* **Instruction Type**: Choose "LLM Transformation" for AI-guided code changes
* **File Pattern**: Specify file types to target (e.g., "*.cs", "*.java", "pom.xml")
* **Transformation Instruction**: Provide detailed markdown instructions

#### Example LLM Transformation Instruction:

```
MarkdownCode



# .NET 8 Migration Guide for C# Files

## General Guidelines:
- Do not change indentation or formatting unless necessary
- Do not remove comments or regions

## C# Language Enhancements:
- Use 'record' for immutable data types
```

### Step 5: Create Your Recipe

Once you've filled in all the information and configured your steps:

1. Review all the details in the form
2. Click the **"Create Recipe"** button in the top right corner
3. Your recipe will be saved and available for use in transcriptions

## Recipe Management Features

### Recipe List Overview

The recipes dashboard shows:

* **Total count**: Number of recipes and total steps across all recipes
* **Search functionality**: Find recipes by name quickly
* **Sorting options**: Filter by Name, Version, or Steps Count
* **Recipe cards**: Each showing name, description, step count, and instruction count

### Available Actions

For each recipe, you can:

* **Edit**: Modify existing recipe configuration and steps
* **Export**: Download the recipe in JSON format for backup or sharing
* **Delete**: Remove recipes that are no longer needed

### Bulk Operations

From the main recipes page:

* **New Recipe**: Create a new migration recipe from scratch
* **Import**: Upload and import existing recipe files
* **Export**: Export multiple recipes at once

## Best Practices

### Recipe Design

1. **Start Simple**: Begin with basic transformations before adding complexity
2. **Be Specific**: Provide clear, actionable instructions
3. **Test Incrementally**: Validate each step individually
4. **Document Dependencies**: Note any prerequisites or assumptions

### Step Organization

1. **Logical Order**: Arrange steps in execution sequence
2. **Dependency Awareness**: Ensure earlier steps prepare for later ones
3. **Error Handling**: Include validation and rollback instructions
4. **Atomic Operations**: Make each step as independent as possible

### Instruction Clarity

1. **Use Examples**: Provide before/after code samples
2. **Specify Context**: Include file paths and locations
3. **Handle Edge Cases**: Address common scenarios and exceptions
4. **Version Specificity**: Be explicit about framework/library versions

## Recipe Examples

### .NET Core Migration

```
MarkdownCode



**Recipe Name**: .NET Core 3.1 to .NET 8 Migration
**Description**: Migration of C# source code and project files from .NET Core 3.1 to .NET 8

**Steps**:
1. Update C# Files
   - File Pattern: *.cs
   - Instructions: Update language features, remove deprecated APIs

2. Update Project Files 
   - File Pattern: *.csproj
   - Instructions: Update target framework, package references
```

### Angular Version Upgrade

```
MarkdownCode



**Recipe Name**: Angular 8 to Angular 9
**Description**: Migration guide for Angular 8 to Angular 9

**Steps**:
1. Update Angular CLI & Core
   - File Pattern: *.ts, *.json
   - Instructions: Update imports, deprecated features
```

## Troubleshooting

### Common Issues

* **Step Ordering**: Ensure dependencies are handled before dependent steps
* **Instruction Clarity**: Be specific about file locations and expected outcomes
* **Version Compatibility**: Verify all components work together
* **Resource Availability**: Confirm required tools and dependencies are accessible

### Tips for Success

* Test recipes on small, representative projects first
* Keep instructions focused and actionable
* Document assumptions and prerequisites
* Regularly update recipes as frameworks evolve

---

Ready to execute your recipe? Learn how to apply it in [Transcriptions →](/legacy-uplift/transcriptions)

Last modified on February 26, 2026

[Analytics](/legacy-uplift/analytics)[Transcriptions](/legacy-uplift/transcriptions)

On this page

* [What are Recipes?](/legacy-uplift/recipes#what-are-recipes)
* [Creating Your First Recipe](/legacy-uplift/recipes#creating-your-first-recipe)
  + [Step 1: Start Creating](/legacy-uplift/recipes#step-1-start-creating)
  + [Step 2: Basic Information](/legacy-uplift/recipes#step-2-basic-information)
  + [Step 3: Add Recipe Steps](/legacy-uplift/recipes#step-3-add-recipe-steps)
  + [Step 4: Configure Instructions](/legacy-uplift/recipes#step-4-configure-instructions)
  + [Step 5: Create Your Recipe](/legacy-uplift/recipes#step-5-create-your-recipe)
* [Recipe Management Features](/legacy-uplift/recipes#recipe-management-features)
  + [Recipe List Overview](/legacy-uplift/recipes#recipe-list-overview)
  + [Available Actions](/legacy-uplift/recipes#available-actions)
  + [Bulk Operations](/legacy-uplift/recipes#bulk-operations)
* [Best Practices](/legacy-uplift/recipes#best-practices)
  + [Recipe Design](/legacy-uplift/recipes#recipe-design)
  + [Step Organization](/legacy-uplift/recipes#step-organization)
  + [Instruction Clarity](/legacy-uplift/recipes#instruction-clarity)
* [Recipe Examples](/legacy-uplift/recipes#recipe-examples)
  + [.NET Core Migration](/legacy-uplift/recipes#net-core-migration)
  + [Angular Version Upgrade](/legacy-uplift/recipes#angular-version-upgrade)
* [Troubleshooting](/legacy-uplift/recipes#troubleshooting)
  + [Common Issues](/legacy-uplift/recipes#common-issues)
  + [Tips for Success](/legacy-uplift/recipes#tips-for-success)

---


# ORIGEM: https://docs.wynxx.app/legacy-uplift/transcriptions
Legacy Uplift

# Transcriptions

Execute your migration recipes here. Transcriptions take the recipes you created and apply them to your actual code repositories.

## What is a Transcription?

A transcription is simply running a migration recipe on your code. It:

* Reads your repository
* Applies your recipe rules
* Creates a new branch with the updated code
* Shows you what changed

## How to Start a Transcription

### 1. Click "New Transcription"

On the dashboard, click the **"New Transcription"** button.

![Start New Transcription Modal](/assets/start-new-transcription-f0VWy2nS.png)

### 2. Fill the Form

You need to provide:

* **LLM**: Pick your AI model (GPT4o, Claude, etc.)
* **VCS**: Choose your Git setup
* **Recipe**: Select the migration recipe to use
* **Repository URL**: Your Git repository link
* **Source Branch**: The branch to migrate from (usually `main`)
* **Target Branch**: Name for the new branch (like `migration/updated`)

### 3. Click Start

Hit **"Start Transcription"** and wait. The system will:

* Download your code
* Apply the recipe changes
* Create a new branch with updated code
* Show you the results

## Transcription Status

Transcriptions can have two statuses:

### Failed

**What happened:** Something went wrong
**Common reasons:**

* Can't access your repository
* Recipe has errors
* AI model failed

**What to do:** Check the error details and try again

### Completed

**What happened:** Migration worked!
**Result:** New branch created with your updated code
**Next step:** Test your migrated code

### History

See all your past transcriptions:

* Which recipe was used
* Source and target branches
* How long it took
* Quick actions menu

![Transcription History](/assets/transcription-history-145X8IIt.png)

## What Affects Speed & Success

**Faster processing:**

* Smaller repositories
* Simple recipes
* Fast AI models
* Good internet connection

**Higher success rate:**

* Clean, simple code
* Well-written recipes
* Correct repository permissions
* Accurate file patterns

## Dashboard Overview

**Main Dashboard Shows:**

* **Summary**: Total transcriptions (active and completed)
* **Active**: Currently running migrations
* **History**: All your past transcriptions
* **Actions**: Refresh, Cleanup, New Transcription buttons

**Click any transcription** to see detailed info:

![Transcription Details Success](/assets/transcription-details-success-BLbu6s_4.png)

**For successful transcriptions:**

* Repository link
* Which branches (source → target)
* File size changes (before → after)
* Lines of code changed
* Processing stats: tokens used, AI model, time taken
* Files processed count

![Transcription Details Failed](/assets/transcription-details-failed-Ba3VyzUN.png)

**For failed transcriptions:**

* Error messages and codes
* What was processed before it failed
* Partial results and debug info

**Multiple transcriptions:** You can run several at once to test different recipes, compare AI models, or create different approaches.

## After Completion

**Check your results:** Review changed files, code updates, and library changes

**Before merging:**

1. **Build**: Make sure your code compiles
2. **Test**: Run your tests to ensure everything works
3. **Review**: Manually check important changes
4. **Test Integration**: Make sure it works with other systems

## Common Issues & Solutions

### When Transcriptions Fail

**Authentication Problems:**

* Check your repository access
* Verify Git credentials
* Make sure the repository URL is correct

**Timeouts:**

* Your repository might be too large
* Break it into smaller parts
* Try during off-peak hours

**Incomplete Changes:**

* Recipe instructions might be unclear
* Check file permissions
* Make sure all dependencies are available

**Branch Errors:**

* Target branch name might already exist
* Check your push permissions
* Follow your team's naming conventions

---

Ready to track your migration progress? Learn about [Analytics →](/legacy-uplift/analytics)

Last modified on February 26, 2026

[Recipes](/legacy-uplift/recipes)[Overview](/legacy-transformer-web/overview)

On this page

* [What is a Transcription?](/legacy-uplift/transcriptions#what-is-a-transcription)
* [How to Start a Transcription](/legacy-uplift/transcriptions#how-to-start-a-transcription)
  + [1. Click "New Transcription"](/legacy-uplift/transcriptions#1-click-new-transcription)
  + [2. Fill the Form](/legacy-uplift/transcriptions#2-fill-the-form)
  + [3. Click Start](/legacy-uplift/transcriptions#3-click-start)
* [Transcription Status](/legacy-uplift/transcriptions#transcription-status)
  + [Failed](/legacy-uplift/transcriptions#failed)
  + [Completed](/legacy-uplift/transcriptions#completed)
  + [History](/legacy-uplift/transcriptions#history)
* [What Affects Speed & Success](/legacy-uplift/transcriptions#what-affects-speed--success)
* [Dashboard Overview](/legacy-uplift/transcriptions#dashboard-overview)
* [After Completion](/legacy-uplift/transcriptions#after-completion)
* [Common Issues & Solutions](/legacy-uplift/transcriptions#common-issues--solutions)
  + [When Transcriptions Fail](/legacy-uplift/transcriptions#when-transcriptions-fail)

---
