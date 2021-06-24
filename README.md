<h1>Periodical Technical Inspection Application</h1>
<h2>Backend - Java</h2>

<p>The application is used to manage a Vehicle Inspection Station.</p>
<p>This repository is the backend part of the project</p>

<h3>Technologies used:</h3>
<p>- Java</p>
<p>- Maven</p>
<p>- Spring framework</p>
<p>- MySQL Database</p>

<h3>Project structure:</h3>
<p>- "controller" package contains classes responsible for processing incoming REST API requests, preparing a model, and returning the view to be rendered as a response;</p>
<p>- "model" package contains classes that mirror the tables in the database and only consists of fields, getters and setters. It also contains classes used for other logic of the application;</p>
<p>- "repository" package contains interfaces used for encapsulating storage, retrieval, and search behavior which emulates a collection of objects;</p>
<p>- "service" package contains classes used for writing the business logic;</p>
<p>- "exception" package contains classes used for handling exceptions;</p>
<p>- PtiApplication class is where the main method is written and the application is run from.</p>
<p>- "resources" contains the database access configurations.</p>

<h3>How to run:</h3>
<p>1. Clone repository.</p>
<p>2. Configure the database access properties.</p>
<p>3. Build and run project</p>
