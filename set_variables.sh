#!/bin/bash

export PROJECT_ID="streaming-batching-test"

export REGION_ID="us-central1"

export TEMP_GCS_BUCKET="dataflow-utility-bucket"

export DATAFLOW_SERVICE_ACCOUNT_NAME="dataflow-service"

export DATAFLOW_SERVICE_ACCOUNT_EMAIL="${DATAFLOW_SERVICE_ACCOUNT_NAME}@${PROJECT_ID}.iam.gserviceaccount.com"

