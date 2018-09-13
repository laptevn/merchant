# Merchant
See task.md for task definition
## Review
The project contains of 3 parts - converter, interpreter and client.<br/>
The converter is used by the interpreter to convert Roman-Arabic digits. The client provides command line interface and uses the interpreter inside.<br/>

## Instructions
- Project can be built using `gradle build` command. Unit tests will be run after building.<br/>
- Application can be run with `build/libs/merchant-1.0-SNAPSHOT.jar test_input.txt` command.<br/>

## Notes
The project uses annotations from "Java Concurrency in Practise" to express thread safety in declarative way. Also Log4J is used to log exceptional cases.<br/>
Both don't help to solve the problem so should be fine from task definition perspective.

## TODOs
1. Hide collections behind abstraction in InterpreterContext.<br/>
I left collections for simplicity and since an abstraction do not provide much value now. But in general we need to hide all redundant functionality (e.g. collection methods that are not proper for answers storage).
2. Split the project on 3 sub projects - converter, interpreter and client.<br/>
Current separation is based on package level for simplicity but we need to physically separate them since they are totally independent.<br/>