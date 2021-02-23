# vdx
vdx-test

This project contains thre api

My assumpution
key-- string 
value -- Integer 

1. Get Key value
    url : http://localhost:8080/vdx/key/{key}
    
2. Put method to add and update the key value
    url : http://localhost:8080/vdx/key/{key}/value/{value}
    method : PUT
    
3. Get average value of non expired keys
    url : http://localhost:8080/vdx/key/average
