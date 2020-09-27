//importing csv file as spark dataframe
val df = spark.read.format("csv").option("header","true").option("inferSchema","true").option("nullvalue","NA").option("escape","\"").option("path","fake_job_postings.csv").load()
//counting the number of lines
println("Them number of lines in csv file is :  ")
df.count()

// the number of fake job postings
println("the number of fake job postings")
val df_f = df.filter("fraudulent==1") 
df_f.count()

// the number of real job postings
println (" the number of real job postings  is: ")
val df_nf = df.filter("fraudulent==0")
df_nf.count()


//top 10 most required education in fake job postings
println("the following table shows the top 10 most required education in fake job postings")
df_f.groupBy($"required_education").agg(Map("required_education"->"count")).sort($"count(required_education)".desc).limit(10).show()

//top 10 most required education in real job postings
println("the following table shows the top 10 most required education in real job postings")
df_nf.groupBy($"required_education").agg(Map("required_education"->"count")).sort($"count(required_education)".desc).limit(10).show()