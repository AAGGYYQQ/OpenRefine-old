package com.google.gridworks.commands.column;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gridworks.commands.Command;
import com.google.gridworks.model.AbstractOperation;
import com.google.gridworks.model.Project;
import com.google.gridworks.operations.column.ColumnMoveOperation;
import com.google.gridworks.process.Process;

public class MoveColumnCommand extends Command {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Project project = getProject(request);
            
            String columnName = request.getParameter("columnName");
            int index = Integer.parseInt(request.getParameter("index"));
            
            AbstractOperation op = new ColumnMoveOperation(columnName, index);
            Process process = op.createProcess(project, new Properties());
            
            performProcessAndRespond(request, response, project, process);
        } catch (Exception e) {
            respondException(response, e);
        }
    }
}