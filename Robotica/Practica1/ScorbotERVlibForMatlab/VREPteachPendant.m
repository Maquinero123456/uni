function varargout = VREPteachPendant(varargin)
% VREPTEACHPENDANT MATLAB code for VREPteachPendant.fig
%      VREPTEACHPENDANT, by itself, creates a new VREPTEACHPENDANT or raises the existing
%      singleton*.
%
%      H = VREPTEACHPENDANT returns the handle to a new VREPTEACHPENDANT or the handle to
%      the existing singleton*.
%
%      VREPTEACHPENDANT('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in VREPTEACHPENDANT.M with the given input arguments.
%
%      VREPTEACHPENDANT('Property','Value',...) creates a new VREPTEACHPENDANT or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before VREPteachPendant_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to VREPteachPendant_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help VREPteachPendant

% Last Modified by GUIDE v2.5 22-Mar-2017 20:48:42

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @VREPteachPendant_OpeningFcn, ...
                   'gui_OutputFcn',  @VREPteachPendant_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before VREPteachPendant is made visible.
function VREPteachPendant_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to VREPteachPendant (see VARARGIN)

% Choose default command line output for VREPteachPendant
handles.output = 0;
handles.scb = varargin{1};
handles.cartesianInc = 0.01;
handles.jointsInc = 40;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes VREPteachPendant wait for user response (see UIRESUME)
uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = VREPteachPendant_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;
close(handles.figure1);

% --- Executes on button press in moveTipPositiveX.
function moveTipPositiveX_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipPositiveX (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in X...');
handles.scb.pendantMoveCartesian(1,handles.cartesianInc);


% --- Executes on button press in moveBaseLeft.
function moveBaseLeft_Callback(hObject, eventdata, handles)
% hObject    handle to moveBaseLeft (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Base joint...');
handles.scb.pendantMove(1,handles.jointsInc);

% --- Executes on button press in moveTipPositiveY.
function moveTipPositiveY_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipPositiveY (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Y...');
handles.scb.pendantMoveCartesian(2,handles.cartesianInc);


% --- Executes on button press in moveShoulderUp.
function moveShoulderUp_Callback(hObject, eventdata, handles)
% hObject    handle to moveShoulderUp (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Shoulder joint...');
handles.scb.pendantMove(2,handles.jointsInc);

% --- Executes on button press in moveTipPositiveZ.
function moveTipPositiveZ_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipPositiveZ (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Z...');
handles.scb.pendantMoveCartesian(3,handles.cartesianInc);


% --- Executes on button press in moveElbowUp.
function moveElbowUp_Callback(hObject, eventdata, handles)
% hObject    handle to moveElbowUp (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Elbow joint...');
handles.scb.pendantMove(3,handles.jointsInc);

% --- Executes on button press in moveTipNegativeX.
function moveTipNegativeX_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipNegativeX (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in X...');
handles.scb.pendantMoveCartesian(1,-handles.cartesianInc);


% --- Executes on button press in moveBaseRight.
function moveBaseRight_Callback(hObject, eventdata, handles)
% hObject    handle to moveBaseRight (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Base joint...');
handles.scb.pendantMove(1,-handles.jointsInc);

% --- Executes on button press in moveTipNegativeY.
function moveTipNegativeY_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipNegativeY (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Y...');
handles.scb.pendantMoveCartesian(2,-handles.cartesianInc);


% --- Executes on button press in moveShoulderDown.
function moveShoulderDown_Callback(hObject, eventdata, handles)
% hObject    handle to moveShoulderDown (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Shoulder joint...');
handles.scb.pendantMove(2,-handles.jointsInc);

% --- Executes on button press in moveTipNegativeZ.
function moveTipNegativeZ_Callback(hObject, eventdata, handles)
% hObject    handle to moveTipNegativeZ (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Z...');
handles.scb.pendantMoveCartesian(3,-handles.cartesianInc);

% --- Executes on button press in moveElbowDown.
function moveElbowDown_Callback(hObject, eventdata, handles)
% hObject    handle to moveElbowDown (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Elbow joint...');
handles.scb.pendantMove(3,-handles.jointsInc);

% --- Executes on button press in openGripper.
function openGripper_Callback(hObject, eventdata, handles)
% hObject    handle to openGripper (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Opening Gripper');
handles.scb.changeGripper(1);

% --- Executes on button press in closeGripper.
function closeGripper_Callback(hObject, eventdata, handles)
% hObject    handle to closeGripper (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Closing Gripper');
handles.scb.changeGripper(0);

% --- Executes on button press in movePitchUp.
function movePitchUp_Callback(hObject, eventdata, handles)
% hObject    handle to movePitchUp (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Pitch joint...');
handles.scb.pendantMove(4,handles.jointsInc);

% --- Executes on button press in movePitchDown.
function movePitchDown_Callback(hObject, eventdata, handles)
% hObject    handle to movePitchDown (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Pitch joint...');
handles.scb.pendantMove(4,-handles.jointsInc);

% --- Executes on button press in rollLeft.
function rollLeft_Callback(hObject, eventdata, handles)
% hObject    handle to rollLeft (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a negative motion in Roll joint...');
handles.scb.pendantMove(5,-handles.jointsInc);

% --- Executes on button press in rollRight.
function rollRight_Callback(hObject, eventdata, handles)
% hObject    handle to rollRight (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Doing a positive motion in Roll joint...');
handles.scb.pendantMove(5,handles.jointsInc);

% --- Executes on button press in enterButton.
function enterButton_Callback(hObject, eventdata, handles)
% hObject    handle to enterButton (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
handles.output = 1;
guidata(hObject,handles);
uiresume(handles.figure1);

% --- Executes on button press in homeButton.
function homeButton_Callback(hObject, eventdata, handles)
% hObject    handle to homeButton (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
disp('Moving to home position...');
handles.scb.home();

% --- Executes on button press in abortButton.
function abortButton_Callback(hObject, eventdata, handles)
% hObject    handle to abortButton (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
handles.output = 0;
guidata(hObject,handles);
uiresume(handles.figure1);
handles.scb.abort();