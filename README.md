# PsipherBackend
Psispher is a passsword manager web app. It is a open source. It can be easily deployed to your AWS anytime you require. This package consist of backend only.

## Why Psipher
Highly secure since you need not trust on any third party application for securely saving your password.

Accessible securely and remotely without giving any chance for dataleak.

## Build with:
* SpringBoot
* DynamoDB
* AWS Serverless SDK

## Resources used for Deployed & Running:
* AWS Lamda
* DynamoDB
* Cloud Formation
* S3
* AWS API Gateway
* AWS Cognito


## How to Deploy:
Clone PsipherBackend to your workspace. In our application, we have included a Maven pom.xml file, and a SAM template.The easiest way to deploy PsipherBackend is it to use the SAM CLI.

Before proceeding, make sure you the [AWS CLI](https://aws.amazon.com/cli/) installed and configured with a set of AWS credentials, and the [SAM CLI](https://github.com/awslabs/aws-sam-cli).
* Using a shell, navigate to the folder for the PsipherBackend application

```
$ cd ~/library-folder/workspace/PsipherBackend
```

To build PsipherBackend Application 
```
./mvnw package
```

If you already have maven installed locally use
```
mvn package
````

To deploy 
* Clean and rebuild the code as a shaded jar, not as a PsipherBackend jar.
```
./mvnw clean package
```
* Create an S3 bucket to hold the application code. This bucket name must be unique across S3, so adjust for your use in the next two steps.
```
aws s3 mb s3://PsipherBackend-lambda
Instead of PsipherBackend-lambda create your own unique name.
```
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
"OutputValue": "https://andhfldf.execute-api.us-east-2.amazonaws.com/Stage/ping"   You will get unique urls like shown.
     

## Running Locally and Testing
To build and run from a packaged jar locally:
```
./mvnw spring-boot:run
```
## With Docker
To build the image. First build the application, then build the docker image
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
Testing can be done using any of the follow
* curl
* by opening the url in web browser
* postman or any similar tools can be used.
Local URL will be like 
```
http://localhost:8080/(psipher-apis)
```

## Maintainers

[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/0)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/0)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/1)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/1)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/2)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/2)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/3)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/3)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/4)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/4)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/5)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/5)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/6)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/6)[![](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/images/7)](https://sourcerer.io/fame/rahulkrishnan221/rahulkrishnan221/PsipherBackend/links/7)

## Contributions
This project welcomes contributions and suggestions. We use GitHub issues for tracking requests and bugs.
