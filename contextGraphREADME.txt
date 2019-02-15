[sorry for bad formatting, this was originally a comment in the contextGraphTest class, I'll fix it later]


/**
	 * Explanation of Objects/Classes etc. used by contextGraph (very long, explains objects first, graph methods at bottom, ToDo's by section)
	 * 
	 * The graph uses object Characteristic, Solution, and Subject
	 * 	Characteristic and Solution both inherit Subject
	 * 	Only attribute currently in Subject is name
	 * 	Only attribute currently in Solution is name (inherited from subject)
	 * 	Attributes
	 * 		name (inherited from subject)
	 * 		List<String> Synonyms
	 * 		List<SolutionMultiplierPair> characteristicSolutions
	 * 			SolutionMultiplierPair represents Solutions for Characteristics and the multiplier value of relationship
	 * 			Used in place of 2d Array (not possible for ArrayList)
	 * 			Currently multiplier is int value, should be changed to double
	 * 			Recommendation for multiplier values is to represent as percentage
	 * 				ie. percentage likelyhood that solution will fix problem for characteristic
	 * 					ex. Anxiety has solution eat healthy, solutions has 10% chance of fixing anxiety, multiplier = 10
	 * 					ex. Insomnia has solution take medicine, solution has 90% chance of fixing insomnia, multiplier = 90
	 * 	Methods
	 * 		Basic getter/setter methods for all attributes
	 * 		Characteristic: methods for using/interacting with characteristicSolutionsList
	 * 	ToDo
	 * 		Current methods/attributes only represent subjects as used by graph, may not represent use of subjects by rest of bot
	 * 		
	 * Characteristics and Solutions are read in from file here and sent to the graph as arrayLists
	 * 	Solutions must be read first, if a characteristic has a solution not in list of possible solutions, assumed to be typo, skipped
	 * 	This may cause issues with adding characteristics/solutions to graph (fix?)
	 * 
	 * The graph has objects Node and Edge
	 * 	Node has the attributes
	 * 		Subject
	 * 			Characteristic of Solution, represents type of node
	 * 			centerNode is only node with Subject attribute = Subject, all others are Characteristic or Solution
	 * 		EdgesToNode
	 * 			List of edges pointing to the node
	 * 			used for graph traversal
	 * 		EdgesFromNode
	 * 			List of edges pointing away from node
	 * 			used for graph traversal
	 * 		Weight
	 * 			basic weight value of node
	 * 			For center node, weight = amount all characteristics in the graph have been incremented by
	 * 			For characteristic nodes, weight = amount of times the characteristic has been reinforced
	 * 			For solution nodes, weight = sum of weights of all edges pointing to the solution
	 *	Methods
	 *		Constructor
	 *			When node is created only attribute required/allowed is Subject of node, edges are added after
	 *		getter/setter methods not complete, only attribute with full getter/setter = weight
	 *		methods to increment weight, used alongside methods in edge (explained further below)
	 *		equals
	 *			if Subject of node is equal to Subject of other node, nodes are considered equal
	 *		toString
	 *		getSubjectName
	 *			used frequently by other objects/classes (particularly by toString methods)
	 *		compareTo
	 *			compares nodes based on weight
	 *			important for returning solution from graph
	 *	ToDo
	 * 		Create getter/setter methods for node attributes
	 * 	Edge attributes
	 * 		Node startNode
	 * 			node edge is pointing away from
	 * 		Node endNode
	 * 			node edge is pointing to
	 * 		Double weightIn
	 * 			weight collected from startNode
	 * 		int multiplier
	 * 			multiplier of edge
	 * 			doesn't make sense to have as int value when weights are doubles, change?
	 * 		Double weightOut
	 * 			weightIn * multiplier
	 * 	Methods
	 * 		Constructors
	 * 			Too many/bloated
	 * 		getters/setters
	 * 		toString
	 * 		compareTo
	 * 			not used (edges not stored in any lists, only referenced from nodes)
	 * 		updateEdgeWeight
	 * 			used to increment graph (explained further below)
	 * 	ToDo
	 * 		simplify constructors
	 * 		change multiplier to double value (may be complicated, referenced in many places/other objects as int)
	 * 
	 * Incrementing graph
	 * 		methods:
	 * 			Graph: incrementCharacteristic(Characteristic, weight)
	 * 			node: incrementWeight, collectWeights
	 * 			edge: updateEdgeWeight
	 * 		explanation
	 * 			When incrementing characteristic in the graph call incrementCharacteristic(characteristic, weight)
	 * 				The characteristic must be found beforehand
	 * 				If a weight is not specified it will be assumed to be 1, if an int value is passed it will be converted to a double
	 * 			incrementCharacteristic will call node.incrementWeight(weight)
	 * 				the node will be collected using the helper method Graph.getNodeFromCharacteristic(characteristic)
	 * 				this method is only called for the initial node being incremented 
	 * 					(methods are recursive for each node connected down from start node)
	 * 			node.incrementCharacteristic will increase the node weight by the specified amount
	 * 			it will then call edge.updateEdgeWeight for each edge pointing away from the characteristic
	 * 			edge.updateEdgeWeight 
	 * 				weightIn will be set to weight of startNode (node edge is pointing away from, node that was incremented)
	 * 				weightOut will be set to weightIn * multiplier
	 * 				node.collectWeights will be called for endNode (node edge is pointing to)
	 * 			node.collectWeights
	 * 				Attribute weightSum = sum of edge.weightOut of all edges pointing to node
	 * 					including edge node.collectWeights called from
	 * 				node weight set to equal weightSum
	 * 				edge.updateEdgeWeight will then be called for all edges pointing away from node
	 * 			continues recursively
	 * 				edge.updateEdgeWeight -> endNode.collectWeights -> edgeAwayFromNode.updateEdgeWeight -> ...
	 * 				stops when node reached that has no edges pointing away from it
	 * 				in practice this is a solution node, recursive pattern only works down from one characteristic node to it's solutions
	 * Node/edge toDo
	 * 	Implement ability to enable/disable nodes/edges
	 * 		no functionality yet created
	 * 		requires commands to be sent to graph (possible with rest of bot?)
	 * 
	 * Graph
	 * 	attributes
	 * 		Node centerNode
	 * 			name is important, checked when incrementing characteristic/solutions/edges
	 * 			if node.subject.getName != "centerNode" -> increment; otherwise no
	 * 		List<Characteristic> characteristicsList
	 * 			sent to graph
	 * 			list of all possible characteristics in graph
	 * 			if characteristicNode is being added to graph, but characteristic is not in characteristicsList, not added to graph
	 * 		List<Solution> solutionsList
	 * 			sent to graph
	 * 			list of all possible solutions in graph
	 * 			if solutionNode is being added to graph, but solution is not in solutionsList, not added to graph
	 * 			if characteristic solution being added to graph, but not in solutionsList, not added to graph
	 * 		List<Node> characteristicNodes
	 * 			list of all characteristic nodes in the graph
	 * 			when characteristic is added to graph, node is added here
	 * 		List<Node> solutionNodes
	 * 			List of all solutions in graph	
	 * 			when solution is added to graph, node is added here
	 * 			necessary for returning solution from graph
	 * 	methods
	 * 		Constructor
	 * 			takes characteristicsList, solutionsList
	 * 			creates centerNode (name MUST be "centerNode")
	 * 			calls addListSolutions()
	 * 				adds all solutions in solutionsList to graph
	 * 			calls addListCharacteristics()
	 * 				adds all characteristics in characteristicsList to graph
	 * 		addListCharacteristics
	 * 			for each characteristic in the characteristicsList, call addCharacteristic(characteristic)
	 * 		addCharacteristic(characteristic)
	 * 			adds characteristic to graph
	 * 			checks
	 * 				characteristic is not already in graph
	 * 				characteristic exists in characteristicsList
	 * 			To add to graph
	 * 				creates new node (chNode)
	 * 				adds chNode to characteristicNodes
	 * 				calls connectCenterToCharacteristic(chNode)
	 * 			For all solutionMultiplierPair in the characteristic
	 * 				checks if solution is in graph
	 * 				if not, calls createSolutionNode(solution)
	 * 				checks if solutionNode is already connected to chNode (should always be false)
	 * 				calls connectCharacteristicToSolution(chNode, solutionNode, multiplier)
	 * 		addListSolutions
	 * 			calls createSolutioNode(solution) for each solution in list
	 * 		createSolutionNode(solution)
	 * 			checks
	 * 				if solution is already in graph
	 * 				if solution is in solutionsList
	 * 			creates solution node sNode
	 * 			adds sNode to solutionNodes
	 * 		addSolution(solution, characteristicNode) 
	 * 			calls createSolutionNode
	 * 				automatically checks solution validity
	 * 			calls connectCharacteristicToSolution(characteristicNode, solutionNode, multiplier)
	 * 		connectCenterToCharacteristic(characteristicNode) 
	 * 			creates new edge from center node to characteristicNode
	 * 			adds edge to centerNode
	 * 			adds edge to characteristicNode
	 * 		connectCharacteristicToSolution(characteristicNode, solutionNode, multiplier)
	 * 			creates new edge from characteristic node to solutionNode with multiplier
	 * 			adds edge to characteristicNode
	 * 			adds edge to solutionNode
	 * 		incrementCharacteristic(characteristic, weight w)
	 * 			increments characteristic node by w
	 * 		getTopSolution
	 * 			returns solution in graph with highest weight
	 * 		getTopSolutionsArray
	 * 			returns array of all solutions in graph sorted by weight
	 * 		Helper methods
	 * 			boolean characteristicInList(characteristic)
	 * 			boolean characteristicInGraph(characteristic)
	 * 			boolean solutionInList(solution)
	 * 			boolean solutionInGraph(solution)
	 * 			Node getCharacteristicNode(characteristic)
	 * 			Node getSolutionNode(solution)
	 * 			boolean checkNodesConnected(node1, node2)
	 * 		toString
	 * 			
	 */