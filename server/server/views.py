from django.template import RequestContext
from django.http import HttpResponse, HttpRequest 
from django.db import models
from django.template import Context, loader
from django.shortcuts import render_to_response, redirect
from django.core.servers.basehttp import FileWrapper
import sqlite3
import base64
import json
import sys
import os
import urllib2
import tempfile, zipfile


def generateNewsPage(request):
	newsTitle = ['Title 1 blah blah', 'Title 2 blah blah']
	newsSummary =[]
	publicEmotion = []
	posComments = []
	negComments = []
	for i in range(0,len(newsTitle)):
		newsSummary.append(['blah blah summary1','blah blah summary2'])
		publicEmotion.append(50 + i)  
		posComments.append(['posComment 1'])
		negComments.append(['neg Comment 2']) 
	dataDict = {"summary": newsSummary , "posComments":posComments ,"negComments":negComments , "publicEmotion" : publicEmotion }
	print dataDict
	return render_to_response('html/template.html', {"newsData": json.dumps(dataDict)})

# def generate_ics(request):
# 	roll_no = request.GET['InputRoll_ics']
# 	file_name = roll_no + '_iCalendar' + '.ics'
# 	file_writer = open(file_name,'w')
# 	content = query.generateIcsContent(roll_no)
# 	file_writer.write(content)
# 	file_writer.close()
# 	wrapper = FileWrapper(open(file_name))
# 	response = HttpResponse(wrapper, content_type='application/audio')
# 	response['Content-Disposition'] = 'attachment; filename=%s' %file_name
# 	response['Content-Length'] = os.path.getsize(file_name)
# 	return response

# def timetable_results(request):
# 	print (request.GET['InputName'])
# 	course,slots=query.timetableQuery(request.GET['InputName'])
# 	data=[]
# 	for i in range(len(slots)):
# 		temp_dict={"course":course[i] , "slot":slots[i]}
# 		data.append(temp_dict)
# 	return render_to_response('html/timetable_template.html', {"obj_as_json": json.dumps(data)})


# def course_options_results(request):
# 	slot = request.GET['InputSlot']
# 	dept = request.GET['InputName']
# 	code = query.getDepartment(dept)
# 	data = query.availableCourses(slot,dept)
# 	return render_to_response('html/course_stats_results.html', {'data_list': data})

# def login_status(request):
# 	# username = request.GET['InputRoll']
# 	# pwd = request.GET['InputPwd']
# 	# encrypted_password = base64.b64encode(pwd.encode('ascii'))
# 	# payload = {'InputRoll': username , 'InputPwd': encrypted_password}
# 	# base_url = 'http://homepages.iitb.ac.in/~meetshah1995/ldap_meet.php'
# 	# a = '?InputRoll='
# 	# b = '&InputPwd='
# 	# url = base_url + a + username + b + encrypted_password.decode("utf-8")
# 	# data = urllib2.urlencode(payload)
# 	# print(str(encrypted_password))
# 	# binary_data = data.encode('ascii')
# 	# req = urllib2.urlopen(url)
# 	# the_page = req.read()
# 	# valid = the_page 
# 	# print(valid.decode('utf-8'),type(valid.decode('utf-8')))
# 	# if valid.decode('utf-8') == 'NONE' :
# 	# 	print('Login Failes')
# 	# 	return redirect('/login/')
# 	# else :
# 	#request.session[0] = True or False
# 	return redirect('/home/')



# def login(request):
# 	# request.session[0] = False
# 	return render_to_response('html/login.html')

# def prof_results (request):
# 	print (request.GET['InputName'])
# 	grade=query.prof_query(request.GET['InputName'])
# 	data=[]
# 	for i in range(len(grade)):
# 		a=grade[i][9]+" "+str(grade[i][7])+" "+str(grade[i][8])
# 		temp_dict={"name":a , "data":grade[i][0:7]}
# 		data.append(temp_dict)
# 	return render_to_response('html/prof_result.html', {"obj_as_json": json.dumps(data)})

# def course_instructor_results(request):
# 	data = query.courseInstructorQuery(request.GET['InputName'])
# 	return render_to_response('html/course_instructor_results.html', {'attributes': data})

# def registered_students_results(request):
# 	if 'InputName' in request.GET:
# 		message = 'You searched for: %r' % request.GET['InputName']
# 	else:
# 		message = 'You submitted an empty form.'
# 	return HttpResponse(message)

# def stalk_results (request):
# 	if 'InputRoll' in request.GET:
# 		if request.GET['InputRoll'] == '13D070003' or request.GET['InputRoll'] == '13D070064':
# 			message = 'Meri Billi Mujhko Meow ?'
# 			return HttpResponse(message)
# 		else:
# 			data = query.stalkQuery(request.GET['InputRoll'])
# 			return render_to_response('html/stalk_results.html', {'data_list': data})
# 	else:
# 		message = 'You submitted an empty form.'
# 		return HttpResponse(message)

# def registered_students_results(request):
# 	data = query.registeredStudentsQuery(request.GET['InputName'])
# 	return render_to_response('html/registered_students_results.html', {'data_list': data})

# def home (request):
# 	return render_to_response('html/index.html')

# def prof (request):
# 	return render_to_response('html/prof.html')

# def course (request):
# 	return render_to_response('html/course_instructor.html')

# def reg_students (request):
# 	return render_to_response('html/registered_students.html')

# def timetable (request):
# 	return render_to_response('html/timetable_generator.html')

# def options (request):
# 	return render_to_response('html/course_stats.html')
