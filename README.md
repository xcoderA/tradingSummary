# TradingSummary

####Assumptions
- 'QUANTITY SHORT SIGN' and 'QUANTITY LONG SIGN' are not relevant and should not be used.
- The Input file will always be in correct format and present in the right path. Exceptions are not handled and program is expected to fail if file not present or in incorrect format.

####How to run
#####Path of input file can be configured with the param application.properties file or with a VM parameter
>input.file.path
>
>eg: input.file.path=F:\\Input.txt
>
>eg: input.file.path=Input.txt  --relative with current folder
>

#####Path to output file can be configure with the param in application.properties file or with a VM parameter
>output.file.path
>
>eg: output.file.path=F:\\Output.txt
>
>eg: output.file.path=Output.txt    --relative with current folder
>

#####Configure number of threads in workpool
>threadpool.size
>
>eg: threadpool.size = 10
>
#####Configure logs
>logging.file.name=TradingSummary.log
>
>logging.file.path=.
>
>logging.level.com.trading=DEBUG
>
#####Command to run
> mvn-spring-boot:run
>
####Limitation
Input file cannot have more lines than Integer.MAX value
