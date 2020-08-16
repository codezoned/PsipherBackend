# Welcome to Psipher Backend ⚙️
 <p>
    <a href="https://github.com/codezoned/PsipherBackend/commits/master" target="_blank">
        <img src="https://img.shields.io/github/commit-activity/y/codezoned/PSipherBackend.svg" alt="GitHub commit activity">
    </a>
    <a href="https://github.com/codezoned/PsipherBackend/graphs/contributors" target="_blank">
        <img src="https://img.shields.io/github/contributors-anon/codezoned/PsipherBackend.svg" alt="GitHub contributors">
    </a>
    <a href="https://packagist.org/packages/codezoned/PsipherBackend" target="_blank">
        <img src="https://img.shields.io/packagist/dt/codezoned/PSipherBackend.svg" alt="Packagist downloads">
    </a>
 </p>

Psipher is a free and open source password managing web application.
It has a serverless architecture and can be deployed locally to the user's personal cloud account, such as AWS.
This package only deals with the backend part of the project.

# Table of Contents
1. [Why Psipher](#why-psipher)
2. [Dependencies](#dependencies)
3. [High Level Design](#high-level-design)
4. [How to deploy](#how-to-deploy)
5. [API documentation and Testing with Swagger UI](#api-documentation-and-testing-with-swagger-ui)

## Why Psipher 💬
1. Once Psipher has been deployed on a system, the independent user has complete **ownership** of the application.

2. Psipher has a serverless architecture:

     * Our prime focus is on user **security** and **privacy**. The passwords are securely stored without the intervention of any third-party applications and this **prevents dataleaks** from occuring.
     * It is highly **reliable**.
     * Requires **very low maintenance**.
     * It is **highly scalable** - if many people want to use the same account to store passwords.
3. Doesn't require any subscription:

    * It is available **free** of cost for students/people having AWS Free Tier account.
    * For rest of the users, there is **minimal payment** for AWS account which is equivalent to a meal from one of your favourite burger joints.
## Dependencies 🔍
* SpringBoot
* DynamoDB
* Passay
* AWS KMS

## High Level Design 📋 [Click here](HLD.md)

## How to Deploy ❓
Clone PsipherBackend to your workspace. In the application, the Maven pom.xml file and a SAM template have been included. The easiest way to deploy PsipherBackend would be to use the SAM CLI.

Before proceeding, make sure you have the [AWS CLI](https://aws.amazon.com/cli/) installed and configured with a set of AWS credentials, and the [SAM CLI](https://github.com/awslabs/aws-sam-cli).
Using a shell, navigate to the folder for the PsipherBackend application:

```
$ cd ~/library-folder/workspace/PsipherBackend
```

To build PsipherBackend Application:
```
./mvnw package
```

If you already have maven installed locally use:
```
mvn package
````

To deploy:
* Clean and rebuild the code as a shaded jar, not as a PsipherBackend jar.
```
./mvnw clean package
```
* Create an S3 bucket to hold the application code. This bucket name must be unique across S3, so adjust for your use in the next two steps.
```
aws s3 mb s3://PsipherBackend-lambda
```
Instead of PsipherBackend-lambda create your own unique name.

* Copy the jar file to the S3 bucket and update the information into a SAM template.
```
aws cloudformation package --template-file template.yml --output-template-file target/output-template.yml --s3-bucket PsipherBackend-lambda
```
* Deploy a Cloudformation stack from the SAM template. We must provide the --capabilities to allow the deploy to succeed because SAM will be creating IAM roles and policies needed to allow the API Gateway to execute the Lambda function.
```
aws cloudformation deploy --template-file target/output-template.yml --stack-name psipherbackend-lambda --capabilities CAPABILITY_IAM
```
* Describe the stack, which will display the URL of the API in the outputs.
```
aws cloudformation describe-stacks --stack-name psipherbackend-lambda
```
"OutputValue": "https://andhfldf.execute-api.us-east-2.amazonaws.com/Stage/ping". You will get unique urls like shown.


## Running Locally and Testing
To build and run from a packaged jar locally:
```
./mvnw spring-boot:run
```
## With Docker
To build the image, first build the application, then build the docker image
```
mvn package -Dboot
docker build -t psipherbackend-lambda
```
## Running with Docker
```
docker run --name psipherbackend-lambda -p 8080:8080 -d psipherbackend-lambda
```
## With SAM
```
$ mvn clean package
$ sam local start-api --template template.yml
```

## Testing Locally
Testing can be done using any of the following:
* curl
* by opening the url in web browser
* postman or any similar tools can be used.
Local URL will be like
```
http://localhost:8080/(psipher-apis)
```

## API documentation and Testing with Swagger UI 🔨 [Click Here](PsipherSwaggerReadme.md)

## Maintainers 💻

[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/0)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/0)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/1)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/1)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/2)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/2)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/3)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/3)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/4)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/4)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/5)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/5)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/6)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/6)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/7)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/7)

## Contributions 💌
This project welcomes any contributions and suggestions. We use GitHub issues for tracking requests and bugs.
