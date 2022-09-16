
//자바 스크립스 객체 

//Member 클래스 만들기 
Member = function(id,name,addr) {
	this.id = id;
	this.name = name;
	this.addr = addr;
};

//메소드(함수) 정의하기 
Member.prototype.setValue = function(id,name,addr) {
	this.id = id;
	this.name = name;
	this.addr = addr;
}

//메소드(함수) 정의--getter도 만들 수 있다
Member.prototype.getValue = function(id,name,addr) {
	return "[" + this.id +"]"+ this.name +"(" + this.addr +")";
}