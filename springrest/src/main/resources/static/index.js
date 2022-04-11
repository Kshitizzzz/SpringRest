/*
function getDataFromAPI(){
			$.ajax({
				url:"/courses",
				type:"GET",
				dataType:"json",
				success:function(data){
					$.each(data,function(index,val){
						console.log("ID : "+data[index].id);
						console.log("Email :"+data[index].email);
						console.log("Username :"+data[index].userName);
					});
					
				},error:function(jqXHR,textStatus,errorThrown){}
			});
		}
		
		
		function postDataFromAPI(){
			
				var modelObj = {
					id:$("#demo_id").val(),
					email:$("#demo_email").val(),
					userName:$("#demo_name").val()
				};
				console.log("Post data "+modelObj.id+ " " +modelObj.email+ "  "+modelObj.userName);
			
				
				$.ajax({
					type:"POST",
					url:"/courses",
					headers:{
						"Content-Type":"application/json"
					},
					data:JSON.stringify(modelObj),
					success:function(data){
						console.log("Post api response" +data);    // failure message
					},
					error:function(jqXHR,textStatus,errorThrown){
						var status = jqXHR.status;
						alert(status+" Mail invalid or already registered user");
					}		
				});
		}
		
		
*/		