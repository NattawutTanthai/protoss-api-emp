package com.example.demo.service

import com.example.demo.model.Department
import com.example.demo.repository.DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeptService {
    @Autowired
    lateinit var deptRepository: DepartmentRepository

    fun getDeptAll(): List<Department> {
        return deptRepository.findAll()
    }
}