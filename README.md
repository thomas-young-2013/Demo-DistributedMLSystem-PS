# DistributedMLSystem
this is the ssp lr (pull and push) with sync problems

|side|problem|
|--|--|
|server|when two different worker upload the update: a += delta|
|worker|when update and clock conflicted in the same time|



MLSystem] 2017-03-18 13:43:12: 6302 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 297 update is: [0.0046943984477261875, -3.959854325032991E-4, -3.7745479275297535E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6302 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:297, gradients:[[2.115593419602478, 2.409129379050063, 3.396668177859515]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6302 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 298 update is: [0.004689921604570919, -3.9560779510937566E-4, -3.7709483395950645E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6302 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:298, gradients:[[2.120283341207049, 2.4087337712549535, 3.3962910830255555]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6303 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 299 update is: [0.004685449030785175, -3.952305180726653E-4, -3.7673521821913954E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6303 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:299, gradients:[[2.1249687902378342, 2.4083385407368807, 3.395914347807336]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6307 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,71 ) - 300 iteration read: [2.1249687902378342, 2.4083385407368807, 3.395914347807336]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6308 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 300 update is: [0.004680980722297459, -3.9485360103523576E-4, -3.763759452197227E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6308 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:300, gradients:[[2.129649770960132, 2.4079436871358455, 3.3955379718621166]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6309 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 301 update is: [0.0046765166750401405, -3.9447704364030064E-4, -3.7601701464838784E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6309 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:301, gradients:[[2.134326287635172, 2.4075492100922053, 3.395161954847468]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6310 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 302 update is: [0.0046720568849496135, -3.9410084553162354E-4, -3.756584261909195E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6310 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:302, gradients:[[2.1389983445201213, 2.407155109246674, 3.394786296421277]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6310 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 303 update is: [0.004667601347965979, -3.9372500635497307E-4, -3.753001795334244E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6310 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:303, gradients:[[2.1436659458680873, 2.406761384240319, 3.394410996241744]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7314 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 304 update is: [2.1483290959281205, 2.406368034714562, 3.394036053967383]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7314 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:304, gradients:[[4.291995041796207, 4.8131294189548814, 6.788447050209127]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7317 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 305 update is: [4.296653744813307, 4.812736444551496, 6.788072465498768]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7318 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:305, gradients:[[8.588648786609514, 9.625865863506377, 13.576519515707895]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7321 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 306 update is: [-0.3348439285248507, -2.0300357945241307, -2.0736410712644595]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7322 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:306, gradients:[[8.253804858084663, 7.595830068982246, 11.502878444443436]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7326 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 307 update is: [7.468565678532215, 2.8996584853582794, 6.664415612085221]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7327 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:307, gradients:[[15.722370536616879, 10.495488554340525, 18.167294056528657]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7330 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 308 update is: [-0.2540180510926368, -1.4602985710223328, -1.6264660735976506]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7330 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:308, gradients:[[15.468352485524242, 9.035189983318192, 16.540827982931006]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7333 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 309 update is: [-0.08209199622036688, -0.4168923372057905, -0.5828564306853391]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7334 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:309, gradients:[[15.386260489303876, 8.618297646112401, 15.957971552245667]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7337 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 310 update is: [-0.026455996912520856, -0.08285699759783557, -0.2413475976296059]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7340 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:310, gradients:[[15.359804492391355, 8.535440648514566, 15.716623954616061]])











[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1312 [INFO ] [pool-1-thread-1] ( LrWorker.java,131 ) - inconsistent case: 296-->300
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1313 [INFO ] [pool-1-thread-250] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 248
Received 439
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1316 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 300update!
Received 440
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1317 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 301update!
Received 441
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1317 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 302update!
Received 442
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1318 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 303update!
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1322 [INFO ] [pool-1-thread-251] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 249
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1327 [INFO ] [pool-1-thread-252] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 250
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1330 [INFO ] [pool-1-thread-253] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 251
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1334 [INFO ] [pool-1-thread-254] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 252
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1338 [INFO ] [pool-1-thread-255] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 253
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1343 [INFO ] [pool-1-thread-256] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 254
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1349 [INFO ] [pool-1-thread-257] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 255
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1353 [INFO ] [pool-1-thread-258] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 256
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1356 [INFO ] [pool-1-thread-259] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 257
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1361 [INFO ] [pool-1-thread-260] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 258
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1363 [INFO ] [pool-1-thread-261] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 259
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1367 [INFO ] [pool-1-thread-262] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 260
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1376 [INFO ] [pool-1-thread-263] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 261
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1383 [INFO ] [pool-1-thread-264] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 262
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1395 [INFO ] [pool-1-thread-265] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 263
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1398 [INFO ] [pool-1-thread-266] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 264
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1403 [INFO ] [pool-1-thread-267] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 265
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1405 [INFO ] [pool-1-thread-268] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 266
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1409 [INFO ] [pool-1-thread-269] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 267
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1413 [INFO ] [pool-1-thread-270] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 268
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1417 [INFO ] [pool-1-thread-271] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 269
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1421 [INFO ] [pool-1-thread-272] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 270
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1431 [INFO ] [pool-1-thread-273] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 271
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1435 [INFO ] [pool-1-thread-274] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 272
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1443 [INFO ] [pool-1-thread-275] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 273
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1444 [INFO ] [pool-1-thread-276] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 274
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1450 [INFO ] [pool-1-thread-277] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 275
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1457 [INFO ] [pool-1-thread-278] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 276
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1457 [INFO ] [pool-1-thread-280] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 278
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1458 [INFO ] [pool-1-thread-281] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 279
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1458 [INFO ] [pool-1-thread-282] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 280
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1458 [INFO ] [pool-1-thread-283] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 281
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1458 [INFO ] [pool-1-thread-284] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 282
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1459 [INFO ] [pool-1-thread-285] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 283
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1459 [INFO ] [pool-1-thread-286] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 284
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1459 [INFO ] [pool-1-thread-287] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 285
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1459 [INFO ] [pool-1-thread-288] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 286
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1459 [INFO ] [pool-1-thread-289] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 287
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1460 [INFO ] [pool-1-thread-290] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 288
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1460 [INFO ] [pool-1-thread-291] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 289
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1460 [INFO ] [pool-1-thread-292] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 290
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1460 [INFO ] [pool-1-thread-293] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 291
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1461 [INFO ] [pool-1-thread-294] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 292
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1461 [INFO ] [pool-1-thread-295] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 293
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1461 [INFO ] [pool-1-thread-296] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 294
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1460 [INFO ] [pool-1-thread-279] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 277
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1461 [INFO ] [pool-1-thread-297] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 295
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1462 [INFO ] [pool-1-thread-298] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 296
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1462 [INFO ] [pool-1-thread-299] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 297
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1462 [INFO ] [pool-1-thread-300] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 298
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1462 [INFO ] [pool-1-thread-301] ( LrWorker.java,203 ) - out-of-dated gradients got: 300--> 299
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1463 [INFO ] [pool-1-thread-302] ( LrWorker.java,207 ) - 303 finish iter 300with param: [2.129649770960132, 2.4079436871358455, 3.3955379718621166]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1520 [INFO ] [pool-1-thread-303] ( LrWorker.java,207 ) - 303 finish iter 302with param: [2.1389983445201213, 2.407155109246674, 3.394786296421277]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 1521 [INFO ] [pool-1-thread-304] ( LrWorker.java,203 ) - out-of-dated gradients got: 303--> 301
Received 443
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2320 [INFO ] [pool-1-thread-305] ( LrWorker.java,207 ) - 304 finish iter 303with param: [2.1436659458680873, 2.406761384240319, 3.394410996241744]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2320 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 304update!
Received 444
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2324 [INFO ] [pool-1-thread-306] ( LrWorker.java,207 ) - 305 finish iter 304with param: [4.291995041796207, 4.8131294189548814, 6.788447050209127]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2324 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 305update!
Received 445
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2328 [INFO ] [pool-1-thread-307] ( LrWorker.java,207 ) - 306 finish iter 305with param: [8.588648786609514, 9.625865863506377, 13.576519515707895]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2328 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 306update!
Received 446
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2332 [INFO ] [pool-1-thread-308] ( LrWorker.java,207 ) - 307 finish iter 306with param: [8.253804858084663, 7.595830068982246, 11.502878444443436]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2332 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 307update!
Received 447
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2337 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 308update!
Received 448
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2341 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 309update!
Received 449
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2344 [INFO ] [pool-1-thread-1] ( LrWorker.java,173 ) - start update 310update!
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 2349 [INFO ] [pool-1-thread-309] ( LrWorker.java,207 ) - 310 finish iter 307with param: [15.722370536616879, 10.495488554340525, 18.167294056528657]



problems here:

> 303 clock reached, in the meantime, 304 updated.

303 update is: [0.004667601347965979, -3.9372500635497307E-4, -3.753001795334244E-4]
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:12: 6310 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,173 ) - Start clock: Carrier(iterationNum:303, gradients:[[2.1436659458680873, 2.406761384240319, 3.394410996241744]])
[ INFO] [DistributedMLSystem] 2017-03-18 13:43:13: 7314 [INFO ] [pool-1-thread-2] ( SSPParameterTable.java,96 ) - 304 update is: [2.1483290959281205, 2.406368034714562, 3.394036053967383]


2.129649770960132, 2.4079436871358455, 3.3955379718621166
