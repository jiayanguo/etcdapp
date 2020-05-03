## Build an webapp using etcd as DB

### Prerequisite
Install minikube:
https://kubernetes.io/docs/tasks/tools/install-minikube/

### Deploy etcd
 
 ```aidl
kubectl apply -f etcd.yaml
```

### Deploy etcd app

`kubectl apply -f deployment.yaml`

### Test 

`minikube service etcd-service`

```
Add record
curl -X PUT \
  'http://192.168.99.102:32001/message/1?name=jguo&body=I%20am%20a%20software%20engineer' \
  -H 'cache-control: no-cache'

Get Record
curl -X GET \
  http://192.168.99.102:32001/message/1 \
  -H 'cache-control: no-cache'

Update Record
curl -X POST \
  'http://192.168.99.102:32001/message/1?name=jiayan' \
  -H 'cache-control: no-cache'

Delete Record
curl -X DELETE \
  http://192.168.99.102:32001/message/1 \
  -H 'cache-control: no-cache'

```

### Reference
https://github.com/etcd-io/etcd/tree/master/hack/kubernetes-deploy
https://github.com/etcd-io/jetcd