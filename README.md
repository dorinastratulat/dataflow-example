Project generated from maven archetype:

gcloud services enable dataflow.googleapis.com


1. Use java data flow archetype:

mvn archetype:generate \
-DarchetypeArtifactId=google-cloud-dataflow-java-archetypes-starter \
-DarchetypeGroupId=com.google.cloud.dataflow \
-DgroupId=com.click.example \
-DartifactId=dataflow-example \
-Dversion="[1.0.0,2.0.0]" \
-DinteractiveMode=false \

2. Run Java main from Maven:

mvn compile exec:java -e \
-Dexec.mainClass=com.simple.example.StarterPipeline \
-Dexec.args="--project=dataflow-test-227715 \
--stagingLocation=gs://example-dataflow-stage/staging/ \
--tempLocation=gs://example-dataflow-stage/temp/ \
--runner=DataflowRunner"


source set_variables.sh

3. Crete CS Bucket 
    gsutil mb gs://${TEMP_GCS_BUCKET}

4. Create Service Account / Key

- create Service Account:
gcloud iam service-accounts create $DATAFLOW_SERVICE_ACCOUNT_NAME \
--project="${PROJECT_ID}" \
--description="Service Account for simple Dataflow pipeline" \
--display-name="Simple dataflow pipeline" \

- add role owner to the newly created Service Account
 gcloud projects add-iam-policy-binding ${PROJECT_ID} \
 --member="serviceAccount:${DATAFLOW_SERVICE_ACCOUNT_EMAIL}" \
 --role=roles/owner
 
 - create and download the key
 gcloud iam service-accounts keys create service-account-key.json \
 --iam-account="${DATAFLOW_SERVICE_ACCOUNT_EMAIL}"
 
 5. Run
 Note: you run as: account = dorina.stratulatt@gmail.com
 
 
 
 mvn compile exec:java -e \
 -Dexec.mainClass=com.simple.example.StarterPipeline \
 -Dexec.args="--project=${PROJECT_ID} \
 --stagingLocation=gs://${TEMP_GCS_BUCKET}/staging/ \
 --tempLocation=gs://${TEMP_GCS_BUCKET}/temp/ \
 --runner=DataflowRunner"
 
Note:

If you want to run as the service account you created:

gcloud auth activate-service-account ${DATAFLOW_SERVICE_ACCOUNT_EMAIL} \
 --key-file=service-account-key.json \
 --project=${PROJECT_ID}
 
 mvn compile exec:java -e \
  -Dexec.mainClass=com.simple.example.StarterPipeline \
  -Dexec.args="--project=${PROJECT_ID} \
  --stagingLocation=gs://${TEMP_GCS_BUCKET}/staging/ \
  --tempLocation=gs://${TEMP_GCS_BUCKET}/temp/ \
  --runner=DataflowRunner"
  
To go back as your user:

gcloud config set account dorina.stratulatt@gmail.com


6. Run Grep
mvn compile exec:java -e \
  -Dexec.mainClass=com.simple.example.Grep \
  -Dexec.args="--project=${PROJECT_ID} \
  --stagingLocation=gs://${TEMP_GCS_BUCKET}/staging/ \
  --tempLocation=gs://${TEMP_GCS_BUCKET}/temp/ \
  --runner=DataflowRunner"
